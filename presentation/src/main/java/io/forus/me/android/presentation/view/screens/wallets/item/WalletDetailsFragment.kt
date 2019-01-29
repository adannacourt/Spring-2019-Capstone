package io.forus.me.android.presentation.view.screens.wallets.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.forus.me.android.presentation.view.base.lr.LRViewState
import io.forus.me.android.presentation.view.base.lr.LoadRefreshPanel
import io.forus.me.android.presentation.R
import io.forus.me.android.presentation.helpers.format
import io.forus.me.android.presentation.internal.Injection
import io.forus.me.android.presentation.view.fragment.ToolbarLRFragment
import kotlinx.android.synthetic.main.fragment_wallet_details.*

class WalletDetailsFragment : ToolbarLRFragment<WalletDetailsModel, WalletDetailsView, WalletDetailsPresenter>(), WalletDetailsView{

    companion object {
        private val WALLET_ID_EXTRA = "WALLET_ID_EXTRA"

        fun newIntent(walletId: Long): WalletDetailsFragment = WalletDetailsFragment().also {
            val bundle = Bundle()
            bundle.putSerializable(WALLET_ID_EXTRA, walletId)
            it.arguments = bundle
        }
    }

    private var walletId: Long = 0

    override fun viewForSnackbar(): View = root

    override val toolbarTitle: String
        get() = getString(R.string.wallet_title_info)

    override val allowBack: Boolean
        get() = true

    override fun loadRefreshPanel(): LoadRefreshPanel = lr_panel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_wallet_details, container, false)
        val bundle = this.arguments
        if (bundle != null) {
            walletId = bundle.getLong(WALLET_ID_EXTRA)
        }
        return view
    }

    override fun createPresenter() = WalletDetailsPresenter(
            walletId,
            Injection.instance.walletsRepository
    )

    override fun render(vs: LRViewState<WalletDetailsModel>) {
        super.render(vs)

        val item = vs.model.item
        tv_balance_crypto.text = if(item != null) ""+item.balance.format(5)+" "+item.currency?.name else ""
        tv_balance_fiat.text = resources.getString(R.string.wallet_balance_fiat, (item?.balance?.times(244.60)).format(2), (item?.balance?.times(281.60)).format(2))
        iv_logo.setImageUrl(item?.logoUrl)
    }
}