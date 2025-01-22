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

import static graphql.Assert.assertNotNull;
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

		AppUser savedUser = appUserService.createUser(appUser);

		assertNotNull(savedUser.getId());
		assertEquals("Test userr", savedUser.getUsername());
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
	void testCreateDiscussion() {
		Discussion discussion = new Discussion();
		discussion.setTitle("Test Title");
		discussion.setContent("Test Content");

		AppUser user = new AppUser();
		user.setUsername("Alice");

		Category category = new Category();
		category.setName("Test category");

		AppUser savedUser = appUserService.createUser(user);

		discussion.setCreatedBy(savedUser);
		discussion.setCategory(category);

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
}
