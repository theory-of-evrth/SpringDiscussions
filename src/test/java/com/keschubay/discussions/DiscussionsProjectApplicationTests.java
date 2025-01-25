package com.keschubay.discussions;

import com.keschubay.discussions.model.AppUser;
import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;
import com.keschubay.discussions.repository.DiscussionRepository;
import com.keschubay.discussions.service.AppUserServiceImpl;
import com.keschubay.discussions.service.CategoryServiceImpl;
import com.keschubay.discussions.service.DiscussionServiceImpl;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static graphql.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DiscussionsProjectApplicationTests {

	@Autowired
	private DiscussionServiceImpl discussionService;

	@Autowired
	private AppUserServiceImpl appUserService;

	@Autowired
	private CategoryServiceImpl categoryService;

	@Autowired
	private DiscussionRepository discussionRepository;

	@Test
	void testCreateAppUser() {
		AppUser appUser = new AppUser();
		appUser.setUsername("Test userr");
		appUser.setRole("USER");

		AppUser savedUser = appUserService.createUser(appUser);

		assertNotNull(savedUser.getId());
		assertEquals("Test userr", savedUser.getUsername());
	}

	@Test
	void testReadByIdAndDeleteAppUser()
	{
		AppUser appUser = new AppUser();
		appUser.setUsername("Test userr2");

		appUser.setRole("USER");

		AppUser createdUser = appUserService.createUser(appUser);

		AppUser savedUser = appUserService.getUserById(createdUser.getId()).orElseThrow();
		assertNotNull(savedUser.getId()); // validate user fetched on read by Id
		assertEquals("Test userr2", savedUser.getUsername());

		appUserService.deleteUser(savedUser);

		Optional<AppUser> fetchedUser = appUserService.getUserById(savedUser.getId());

		assertTrue(fetchedUser.isEmpty());

	}

	@Test
	void testCreateCategory()
	{
		Category category = new Category();
		category.setName("Test category");

		Category savedCategory = categoryService.createCategory(category);

		assertNotNull(savedCategory.getId());
		assertEquals("Test category", savedCategory.getName());

		categoryService.deleteCategory(savedCategory);
	}

	@Test
	void testReadByIdAndDeleteCategory()
	{
		Category category = new Category();
		category.setName("Test category");

		Category createdCategory = categoryService.createCategory(category);

		Category savedCategory = categoryService.getCategoryById(createdCategory.getId()).orElseThrow();
		assertNotNull(savedCategory.getId()); // validate user fetched on read by Id
		assertEquals("Test category", savedCategory.getName());

		// Now deleting the category we created and seeing if it still exists
		categoryService.deleteCategory(savedCategory);

		Optional<Category> fetchedCategory = categoryService.getCategoryById(createdCategory.getId());

		assertTrue(fetchedCategory.isEmpty());
	}

	@Test
	void testEditCategory()
	{
		Category category = new Category();

		category.setName("Test category");
		Category savedCategory = categoryService.createCategory(category);
		savedCategory.setName("Testtt category");
		Category editedCategory = categoryService.editCategory(savedCategory);

		assertNotNull(editedCategory);
		assertEquals("Testtt category", editedCategory.getName());

		categoryService.deleteCategory(savedCategory);
	}

	@Test
	void testCreateDiscussion() {
		Discussion discussion = new Discussion();
		discussion.setTitle("Test Title");
		discussion.setContent("Test Content");

		AppUser user = new AppUser();
		user.setUsername("Alice88");
		user.setRole("USER");

		Category category = new Category();
		category.setName("Test category");

		AppUser savedUser = appUserService.createUser(user);
		Category savedCategory = categoryService.createCategory(category);

		discussion.setCreatedBy(savedUser);
		discussion.setCategory(savedCategory);

		Discussion savedDiscussion = discussionService.createDiscussion(discussion);

		assertNotNull(savedDiscussion.getId());
		assertEquals("Test Title", savedDiscussion.getTitle());

		// cleaning
		categoryService.deleteCategory(savedCategory);
	}

	@Test
	void testEditDiscussion() {
		AppUser user = new AppUser();
		user.setUsername("Alice22");
		user.setRole("USER");

		AppUser savedUser = appUserService.createUser(user);

		Discussion discussion = new Discussion();
		discussion.setTitle("Test Title");
		discussion.setContent("Test Content");
		discussion.setCreatedBy(savedUser);

		Discussion savedDiscussion = discussionService.createDiscussion(discussion);
		savedDiscussion.setTitle("Test Title 2");
		savedDiscussion = discussionService.updateDiscussion(savedDiscussion);

		// the object is required to be null, because we are trying to edit the Discussion as an outsider here
		// so in the editing it does not pass condition of either owning this object or being an Admin, and instead of returning edited object, returns null
		// succesfull editing only tested via Postman, because of the Authentification
		assertNull(savedDiscussion);
	}

	@Test
	void testReadByIdAndDeleteDiscussion() {
		Discussion discussion = new Discussion();
		discussion.setTitle("Test Title");
		discussion.setContent("Test Content");

		Discussion savedDiscussion = discussionService.createDiscussion(discussion);

		Optional<Discussion> fetchedDiscussion = discussionService.getDiscussion(savedDiscussion.getId());

		assertFalse(fetchedDiscussion.isEmpty());
		assertEquals(savedDiscussion.getTitle(), fetchedDiscussion.get().getTitle());

		discussionService.deleteDiscussion(savedDiscussion);

		Optional<Discussion> nonexistantDiscussion = discussionService.getDiscussion(savedDiscussion.getId());

		assertTrue(nonexistantDiscussion.isEmpty());
	}

	@Test
	void testAddCommentToDiscussionThenDeleteComment() {
		Discussion discussion = new Discussion();
		discussion.setTitle("Summer camp");
		discussion.setContent("Test Content");

		AppUser user = new AppUser();
		user.setUsername("Drake123");
		user.setRole("USER");

		AppUser savedUser = appUserService.createUser(user);

		discussion.setCreatedBy(savedUser);

		Discussion savedDiscussion = discussionService.createDiscussion(discussion);

		Comment comment = new Comment();
		comment.setDiscussion(discussion);
		comment.setContent("Hi! Who can tell me about that one camp <link> ?");
		comment.setCreatedBy(savedUser);

		Comment addedComment = discussionService.addComment(savedDiscussion.getId(), comment);

		assertNotNull(addedComment);
		assertEquals(addedComment.getContent(), comment.getContent());

		discussionService.deleteComment(addedComment.getId());

		Optional<Comment> fetchedComment = discussionService.getComment(addedComment.getId());
		assertTrue(fetchedComment.isEmpty());
	}

	@Test
	void testGetAllDiscussionsByCategory()
	{
		AppUser user = new AppUser();
		user.setUsername("Bob55");
		user.setRole("ROLE_ADMIN");

		AppUser savedUser = appUserService.createUser(user);

		Category category = new Category();
		category.setName("Digital Media");

		Category savedCategory = categoryService.createCategory(category);

		Discussion discussion1 = new Discussion();
		Discussion discussion2 = new Discussion();
		discussion1.setTitle("Movies");
		discussion2.setTitle("Music");
		discussion1.setCreatedBy(savedUser);
		discussion2.setCreatedBy(savedUser);
		discussion1.setContent("Let's discuss our favourite movies here");
		discussion2.setContent("-");
		discussion1.setCategory(savedCategory);
		discussion2.setCategory(savedCategory);

		Discussion saved1 = discussionService.createDiscussion(discussion1);
		Discussion saved2 = discussionService.createDiscussion(discussion2);

		List<Discussion> created = List.of(saved1, saved2);

		List<Discussion> fetched = discussionService.getAllDiscussions(category.getId());

		List<String> names1 = fetched.stream().map(Discussion::getTitle).sorted().toList();
		List<String> names2 = created.stream().map(Discussion::getTitle).sorted().toList();

		assertEquals(created.size(), fetched.size());
		assertEquals(names1.get(0), names2.get(0));
		assertEquals(names1.get(1), names2.get(1));

		// cleaning to prevent other tests from working incorrectly
		categoryService.deleteCategory(savedCategory);
	}

	@Test
	void testGetAllCategories()
	{
		Category category1 = new Category();
		category1.setName("Digital Media");

		Category savedCategory1 = categoryService.createCategory(category1);

		Category category2 = new Category();
		category2.setName("Natural Sciences");

		Category savedCategory2 = categoryService.createCategory(category2);

		List<Category> created = List.of(savedCategory1, savedCategory2);

		List<Category> fetched = categoryService.getAllCategories();

		List<String> names1 = fetched.stream().map(Category::getName).sorted().toList();
		List<String> names2 = created.stream().map(Category::getName).sorted().toList();

		assertEquals(created.size(), fetched.size());
		assertEquals(names1.get(0), names2.get(0));
		assertEquals(names1.get(1), names2.get(1));
	}

	@Test
	void testGetAllCommentsOfDiscussion()
	{
		Discussion discussion = new Discussion();
		discussion.setTitle("Test Title");
		discussion.setContent("Test Content");

		AppUser user = new AppUser();
		user.setUsername("Alice");
		user.setRole("USER");

		Category category = new Category();
		category.setName("Test category");

		AppUser savedUser = appUserService.createUser(user);
		Category savedCategory = categoryService.createCategory(category);

		discussion.setCreatedBy(savedUser);
		discussion.setCategory(savedCategory);

		Discussion savedDiscussion = discussionService.createDiscussion(discussion);

		Comment comment = new Comment();
		comment.setDiscussion(discussion);
		comment.setContent("Hi!");
		comment.setCreatedBy(savedUser);

		Comment addedComment = discussionService.addComment(savedDiscussion.getId(), comment);

		assertNotNull(addedComment);
		assertEquals(addedComment.getContent(), comment.getContent());

		List<Comment> created = List.of(comment);

		List<Comment> fetched = discussionService.getAllComments(savedDiscussion.getId());

		assertEquals(created.size(), fetched.size());
		assertEquals(created.get(0).getContent(), fetched.get(0).getContent());
	}
	
}
