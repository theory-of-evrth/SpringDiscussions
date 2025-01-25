package com.keschubay.discussions;

import com.keschubay.discussions.model.AppUser;
import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.model.Discussion;
import com.keschubay.discussions.repository.DiscussionRepository;
import com.keschubay.discussions.service.AppUserServiceImpl;
import com.keschubay.discussions.service.CategoryServiceImpl;
import com.keschubay.discussions.service.DiscussionServiceImpl;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static graphql.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DiscussionsProjectApplicationTests {

	@Test
	void contextLoads() {
	}

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
		appUser.setUsername("Test userr");

		appUser.setRole("USER");

		AppUser createdUser = appUserService.createUser(appUser);

		AppUser savedUser = appUserService.getUserById(createdUser.getId()).orElseThrow();
		assertNotNull(savedUser.getId()); // validate user fetched on read by Id
		assertEquals("Test userr", savedUser.getUsername());

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
		// not implemented
		assertTrue(false);
	}

	@Test
	void testCreateDiscussion() {
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

		assertNotNull(savedDiscussion.getId());
		assertEquals("Test Title", savedDiscussion.getTitle());
	}

	@Test
	void testEditDiscussion() {
		Discussion discussion = new Discussion();
		discussion.setTitle("Test Title");
		discussion.setContent("Test Content");

		Discussion savedDiscussion = discussionService.createDiscussion(discussion);
		savedDiscussion.setTitle("Test Title 2");
		savedDiscussion = discussionService.updateDiscussion(savedDiscussion);

		assertNotNull(savedDiscussion.getId());
		assertEquals("Test Title 2", savedDiscussion.getTitle());
	}

	@Test
	void testReadByIdAndDeleteDiscussion() {
		Discussion discussion = new Discussion();

		assertTrue(false);
	}

	@Test
	void testAddCommentToDiscussion() {
		assertTrue(false);
	}

	@Test
	void testDeleteCommentFromDiscussion() {
		assertTrue(false);
	}

	/*
	@Test
	void

	 */
}
