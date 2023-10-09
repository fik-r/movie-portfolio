//package com.example.usecase
//
//import com.example.abstraction.repository.MovieRepository
//import com.example.common.Result
//import com.example.usecase.datahelper.DataHelper
//import io.mockk.coEvery
//import io.mockk.spyk
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import kotlinx.coroutines.test.TestCoroutineScope
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.setMain
//import org.junit.After
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Test
//
//class UseCaseTest {
//    private val testDispatcher = TestCoroutineDispatcher()
//    private val testScope = TestCoroutineScope(testDispatcher)
//    private val movieRepository: MovieRepository = spyk()
//
//    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase by lazy {
//        DeleteFavoriteMovieUseCase(
//            movieRepository
//        )
//    }
//    private val findfavoriteGamesUseCase: FindFavoriteMovieUseCase by lazy {
//        FindFavoriteMovieUseCase(
//            movieRepository
//        )
//    }
//    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase by lazy {
//        GetFavoriteMovieUseCase(
//            movieRepository
//        )
//    }
//    private val getMovieUseCase: GetMovieUseCase by lazy { GetMovieUseCase(movieRepository) }
//    private val getGameUseCase: GetGameUseCase by lazy { GetGameUseCase(movieRepository) }
//    private val insertFavoriteUseCase: InsertFavoriteMovieUseCase by lazy {
//        InsertFavoriteMovieUseCase(
//            movieRepository
//        )
//    }
//    private val searchMovieUseCase: SearchMovieUseCase by lazy { SearchMovieUseCase(movieRepository) }
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun `should deleteFavoriteGame return success when state under test`() {
//        testScope.launch {
//            coEvery {
//                movieRepository.deleteMovie(DataHelper.dataMovie)
//            }
//
//            when (val result = deleteFavoriteMovieUseCase.execute(DataHelper.dataMovie)) {
//                is Result.Success -> {
//                    Assert.assertTrue("success delete favorite game from db", true)
//                }
//
//                is Result.Error -> {
//                    Assert.fail("network trouble")
//                }
//            }
//        }
//    }
//
//    @Test
//    fun `should findFavoriteGameUseCase return data when state under test`() {
//        testScope.launch {
//            coEvery {
//                movieRepository.findMovie(1)
//            }
//
//            when (val result = findfavoriteGamesUseCase.execute(1)) {
//                is Result.Success -> {
//                    Assert.assertEquals(result, DataHelper.dataMovie)
//                }
//
//                is Result.Error -> {
//                    Assert.fail("network trouble")
//                }
//            }
//        }
//    }
//
//    @Test
//    fun `should getFavoriteGames return data when state under test`() {
//        testScope.launch {
//            coEvery {
//                movieRepository.fetchMovieFromDb()
//            }
//
//            when (val result = getFavoriteMovieUseCase.execute()) {
//                is Result.Success -> {
//                    Assert.assertEquals(result, DataHelper.dataMovies)
//                }
//
//                is Result.Error -> {
//                    Assert.fail("network trouble")
//                }
//            }
//        }
//    }
//
//    @Test
//    fun `should getGames return data when state under test`() {
//        testScope.launch {
//            coEvery {
//                movieRepository.fetchMovieFromDb()
//            }
//
//            when (val result = getMovieUseCase.execute()) {
//                is Result.Success -> {
//                    Assert.assertEquals(result, DataHelper.dataMovies)
//                }
//
//                is Result.Error -> {
//                    Assert.fail("network trouble")
//                }
//            }
//        }
//    }
//
//    @Test
//    fun `should getGame return data when state under test`() {
//        testScope.launch {
//            coEvery {
//                movieRepository.fetchMovie("Lorem Ipsum", "1")
//            }
//
//            when (val result = getGameUseCase.execute(GetGameUseCase.Params("Lorem Ipsum", "1"))) {
//                is Result.Success -> {
//                    Assert.assertEquals(result, DataHelper.dataMovie)
//                }
//
//                is Result.Error -> {
//                    Assert.fail("network trouble")
//                }
//            }
//        }
//    }
//
//    @Test
//    fun `should insertFavorite return data when state under test`() {
//        testScope.launch {
//            coEvery {
//                movieRepository.insertMovie(DataHelper.dataMovie)
//            }
//
//            when (val result = insertFavoriteUseCase.execute(DataHelper.dataMovie)) {
//                is Result.Success -> {
//                    Assert.assertTrue(true)
//                }
//
//                is Result.Error -> {
//                    Assert.fail("network trouble")
//                }
//            }
//        }
//    }
//
//    @Test
//    fun `should searchGame return data when state under test`() {
//        testScope.launch {
//            val param = SearchMovieUseCase.Params("Lorem Ipsum", "Lorem Ipsum", 1)
//            coEvery {
//                movieRepository.searchMovie(param.key, param.query, param.page)
//            }
//
//            when (val result = searchMovieUseCase.execute(param)) {
//                is Result.Success -> {
//                    Assert.assertEquals(result, DataHelper.dataMovies)
//                }
//
//                is Result.Error -> {
//                    Assert.fail("network trouble")
//                }
//            }
//        }
//    }
//
//
//}