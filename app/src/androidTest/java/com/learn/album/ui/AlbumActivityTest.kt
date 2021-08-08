package com.learn.album.ui


import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumActivityTest {

  @Rule
  @JvmField
  var mActivityTestRule = ActivityTestRule(AlbumActivity::class.java)

  @Test
  fun albumActivityTest() {
    Thread.sleep(500)
  }
}
