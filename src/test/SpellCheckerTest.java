package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpellCheckerTest {

	@Test
	void testGetNumWords() {

        SpellChecker checker = new SpellChecker();
        int words = checker.getNumWords();
        assertEquals(0, words, "getNumWords should return the number of words in the spellchecker.");
	}

    @Test
    void testAddWordIncreasesCount() {
        SpellChecker checker = new SpellChecker(new String[] { "a", "fox", "jumps", "over", "the", "lazy", "dog" });
        
        
        if (checker.getNumWords() != 7) {
            fail("Checker does not have the correct number of words. Check constructor.");
        } 

        checker.addWord("john");

        assertEquals(8, checker.getNumWords(), "Adding a unique word after initialization should change the count.");
    }

    @Test
    void testDuplicateWordDoesNotIncreaseCount() {
        SpellChecker checker = new SpellChecker();
        checker.addWord("apple");
        checker.addWord("apple");
        assertEquals(1, checker.getWordCount(), "Duplicate words should not change the count.");
    }

    @Test
    void testCorrectlySpelledWordReturnsTrue() {
        SpellChecker checker = new SpellChecker();
        checker.addWord("banana");
        assertTrue(checker.isSpelledCorrectly("banana"), "Should recognize 'banana' as a correctly spelled word.");
    }

    @Test
    void testIncorrectlySpelledWordReturnsFalse() {
        SpellChecker checker = new SpellChecker();
        checker.addWord("banana");
        assertFalse(checker.isSpelledCorrectly("banan"), "Should recognize 'banan' as an incorrectly spelled word.");
    }

    @Test
    void testCaseCorrectWordReturnsTrue() {
        SpellChecker checker = new SpellChecker();
        checker.addWord("banana");
        assertTrue(checker.isSpelledCorrectly("bAnAnA"), "Should recognize 'bAnAnA' as a correctly spelled word.");
    }

    @Test
    void testRecommendClosestAlphabeticalWord() {

        SpellChecker checker = new SpellChecker();
        checker.addWord("bank");
        checker.addWord("bark");
        checker.addWord("apple");

        assertAll("Test Cases",
            () -> assertEquals("bank", checker.getRecommendation("bamk")),
            () -> assertEquals("bark", checker.getRecommendation("bazk")),
            () -> assertEquals("apple", checker.getRecommendation("appke"))
        );   
    }

    @Test
    void testRecommendCorrectWord() {
        SpellChecker checker = new SpellChecker();

        checker.addWord("bank");
        checker.addWord("bark");
        checker.addWord("apple");

        assertAll("Test Cases",
            () -> assertEquals("bank", checker.getRecommendation("bank")),
            () -> assertEquals("bark", checker.getRecommendation("bark")),
            () -> assertEquals("apple", checker.getRecommendation("apple"))
        );   
    }

    @Test
    void testIncorrectFormattingReturnsCorrectRec() {
        SpellChecker checker = new SpellChecker();

        checker.addWord("bank");
        checker.addWord("bark");
        checker.addWord("apple");

        assertAll("Test Cases",
            () -> assertEquals("bank,", checker.getFormatRecommendation("bank ,")),
            () -> assertEquals("bark; ", checker.getFormatRecommendation("bark    ; ")),
            () -> assertEquals("'apple'", checker.getFormatRecommendation("'  apple ' "))
        );   
    }

    @Test
    void testCorrectFormattingReturnsOriginal() {
        SpellChecker checker = new SpellChecker();

        checker.addWord("bank");
        checker.addWord("bark");
        checker.addWord("apple");

        assertAll("Test Cases",
            () -> assertEquals("bank,", checker.getFormatRecommendation("bank,")),
            () -> assertEquals("bark; ", checker.getFormatRecommendation("bark; ")),
            () -> assertEquals("'apple'", checker.getFormatRecommendation("'apple'"))
        );   
    }



}
