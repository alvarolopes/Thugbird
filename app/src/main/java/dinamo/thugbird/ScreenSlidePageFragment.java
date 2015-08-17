package dinamo.thugbird;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ScreenSlidePageFragment extends Fragment {

    public static final String ARG_PAGE = "page";

    private int mPageNumber;
    private ImageView imageResult;

    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        imageResult = (ImageView) rootView.findViewById(R.id.thugColor);
        imageResult.setImageDrawable(getResources().getDrawable(getThugBitmap(mPageNumber)));

        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }


    private int getThugBitmap(int position) {

        switch (position) {
            case 0:
                return R.drawable.thug_red;
            case 1:
                return R.drawable.thug_blue;
            case 2:
                return R.drawable.thug_brown;
            case 3:
                return R.drawable.thug_green;
            case 4:
                return R.drawable.thug_yellow;
            default:
                return R.drawable.thug_brown;
        }
    }
}
