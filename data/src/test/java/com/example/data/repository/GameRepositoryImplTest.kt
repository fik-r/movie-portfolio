//package com.example.data.repository
//
//import com.example.abstraction.datasource.GameLocalDataSource
//import com.example.abstraction.datasource.GameRemoteDataSource
//import com.example.common.Result
//import com.example.data.datahelper.DataHelper
//import com.example.model.Game
//import com.example.model.SearchGame
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
//class GameRepositoryImplTest {
//    private val testDispatcher = TestCoroutineDispatcher()
//    private val testScope = TestCoroutineScope(testDispatcher)
//    private val local: GameLocalDataSource = spyk()
//    private val remote: GameRemoteDataSource = spyk()
//    private val gameRepositoryImpl: GameRepositoryImpl by lazy { GameRepositoryImpl(remote, local) }
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
//    fun `should fetchGame return list game data when state under test`() {
//        testScope.launch {
//            coEvery { remote.fetchGames("Lorem Ispum") } returns DataHelper.gamesRemoteData
//
//            val result: Result<List<Game>> = gameRepositoryImpl.fetchGames("Lorem Ispum")
//
//            Assert.assertEquals(result, DataHelper.gamesRemoteData)
//
//        }
//    }
//
//    @Test
//    fun `should fetchGame return specific game data when state under test`() {
//        testScope.launch {
//            coEvery { remote.fetchGame("Lorem Ispum", "0") } returns DataHelper.gameRemoteData
//
//            val result: Result<Game> = gameRepositoryImpl.fetchGame("Lorem Ipsum", "0")
//
//            Assert.assertEquals(result, DataHelper.gameRemoteData)
//
//        }
//    }
//
//    @Test
//    fun `should searchGame return specific list game data when state under test`() {
//        testScope.launch {
//            coEvery {
//                remote.searchGames(
//                    "test",
//                    page = 1,
//                    query = "Lorem Ipsum"
//                )
//            } returns DataHelper.searchGamesRemoteData
//
//            val result: Result<SearchGame> = gameRepositoryImpl.searchGame(
//                "test",
//                page = 1,
//                query = "Lorem Ipsum"
//            )
//
//            Assert.assertEquals(result, DataHelper.searchGamesRemoteData)
//
//        }
//    }
//
//    @Test
//    fun `should fetchGamesFromDb return list game data when state under test`() {
//        testScope.launch {
//            coEvery {
//                local.fetchGames()
//            } returns DataHelper.dataGames
//
//            val result: List<Game> = gameRepositoryImpl.fetchGameFromDb()
//
//            Assert.assertEquals(result, DataHelper.dataGames)
//        }
//    }
//
//    @Test
//    fun `should findGame return specific game data when state under test`() {
//        testScope.launch {
//            coEvery {
//                local.findGame(1)
//            } returns DataHelper.dataGame
//
//            val result: Game = gameRepositoryImpl.findGame(1)
//
//            Assert.assertEquals(result, DataHelper.dataGame)
//        }
//    }
//
//    @Test
//    fun `should insertGame return success state under test`() {
//        testScope.launch {
//            coEvery {
//                local.insertGame(DataHelper.dataGame)
//            }
//        }
//    }
//
//    @Test
//    fun `should deleteGame return success state under test`() {
//        testScope.launch {
//            coEvery {
//                local.deleteGame(DataHelper.dataGame)
//            }
//        }
//    }
//}