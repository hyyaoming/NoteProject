package note.lym.org.noteproject.fragment;

import com.tjerkw.slideexpandable.library.SlideExpandableListView;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.PersonToLifeAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.MoreType;
import note.lym.org.noteproject.presenter.note.more.life.IPersionToLifeView;
import note.lym.org.noteproject.presenter.note.more.life.PersonToLifePresenter;

/**
 * 人生百态
 *
 * @author yaoming.li
 * @since 2017-05-16 18:06
 */
public class PersonToLifeFragment extends BaseFragment<PersonToLifePresenter> implements IPersionToLifeView {

    @BindView(R.id.expandable_lv)
    SlideExpandableListView mList;
    private PersonToLifeAdapter mApter;

    @Override
    protected void loadLazyData() {
        mPresenter.getMoreTypeData();
    }

    @Override
    protected void updateViews() {
        mApter = new PersonToLifeAdapter(getActivity());
        mList.setAdapter(mApter,R.id.expandable_toggle_button,R.id.gv_note,R.id.iv_expandable_arrow,0,getActivity());
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_person_to_life;
    }

    @Override
    public void getMoreTypeData(List<MoreType.ShowapiResBodyBean.ListBeanX> listBeanXes) {
        mApter.setData(listBeanXes);
    }
}
