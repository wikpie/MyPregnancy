package wiktor.pienko.mypregnancy.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BasePresenter<BaseView>> : Fragment(){

    protected lateinit var presenter: P

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = instantiatePresenter()
        return super.onCreateView(inflater, container, savedInstanceState)
    }



    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P


}