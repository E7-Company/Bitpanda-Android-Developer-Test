package com.bitpanda.developertest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bitpanda.developertest.model.Cryptocoin
import com.bitpanda.developertest.model.Fiat
import com.bitpanda.developertest.model.Metal
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.repository.Repository
import com.bitpanda.developertest.ui.wallets.WalletViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ViewModelTest {

    @Mock
    private lateinit var context: Application

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    private lateinit var repository: Repository
    private lateinit var viewModel: WalletViewModel

    private lateinit var onRenderWalletsObserver: Observer<List<Wallet>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        `when`(context.applicationContext).thenReturn(context)

        Dispatchers.setMain(testCoroutineDispatcher)

        repository = Repository()
        viewModel = WalletViewModel(repository)

        onRenderWalletsObserver = mock(Observer::class.java) as Observer<List<Wallet>>

    }

    @Test
    fun `retrieve all wallets from Repository`() {
        with(viewModel) {
            wallets.observeForever(onRenderWalletsObserver)
        }
        repository.getWalletsFlow()
        Assert.assertEquals(viewModel.wallets.value?.size, 8)
    }


    @Test
    fun `retrieve all wallets from Repository sorted by currency type and balance`() {
        with(viewModel) {
            wallets.observeForever(onRenderWalletsObserver)
        }
        repository.getWalletsFlow()
        Assert.assertThat(viewModel.wallets.value?.get(0)?.currency, instanceOf(Fiat::class.java))
        Assert.assertTrue(viewModel.wallets.value?.get(0)?.balance!! > viewModel.wallets.value?.get(1)?.balance!!)
    }

    @Test
    fun `retrieve wallets from Repository filtered by Cryptocoin currency type`() {
        testCoroutineScope.runBlockingTest {
            with(viewModel) {
                wallets.observeForever(onRenderWalletsObserver)
                changeCurrency()
            }
            Assert.assertEquals(viewModel.wallets.value?.size, 3)
            Assert.assertThat(viewModel.wallets.value?.get(0)?.currency, instanceOf(Cryptocoin::class.java))
        }
    }

    @Test
    fun `retrieve wallets from Repository filtered by Fiat currency type`() {
        testCoroutineScope.runBlockingTest {
            with(viewModel) {
                wallets.observeForever(onRenderWalletsObserver)
                changeCurrency()
                changeCurrency()
            }
            Assert.assertEquals(viewModel.wallets.value?.size, 2)
            Assert.assertThat(viewModel.wallets.value?.get(0)?.currency, instanceOf(Fiat::class.java))
        }
    }

    @Test
    fun `retrieve wallets from Repository filtered by Metal currency type`() {
        testCoroutineScope.runBlockingTest {
            with(viewModel) {
                wallets.observeForever(onRenderWalletsObserver)
                changeCurrency()
                changeCurrency()
                changeCurrency()
            }
            Assert.assertEquals(viewModel.wallets.value?.size, 3)
            Assert.assertThat(viewModel.wallets.value?.get(0)?.currency, instanceOf(Metal::class.java))
        }
    }
}