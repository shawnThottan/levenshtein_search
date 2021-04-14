package com.shawnthottan.searcher;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearcherApplication.class)
class SearcherApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getTextError_WhenTextParameterIsAbsent()
			throws Exception {
		mvc.perform(get("/")
				.param("word", "word")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Error: Parameter 'text' is empty"));
	}

	@Test
	public void getTextError_WhenTextParameterIsEmpty()
			throws Exception {
		mvc.perform(get("/")
				.param("text", "")
				.param("word", "word")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Error: Parameter 'text' is empty"));
	}

	@Test
	public void getWordError_WhenWordParameterIsAbsent()
			throws Exception {
		mvc.perform(get("/")
				.param("text", "text")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Error: Parameter 'word' is empty"));
	}

	@Test
	public void getWordError_WhenWordParameterIsEmpty()
			throws Exception {
		mvc.perform(get("/")
				.param("text", "text")
				.param("word", "")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Error: Parameter 'word' is empty"));
	}

	@Test
	public void getPass_WhenWord()
			throws Exception {
		mvc.perform(get("/")
				.param("text", "Word Words Wor word")
				.param("word", "Word")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
