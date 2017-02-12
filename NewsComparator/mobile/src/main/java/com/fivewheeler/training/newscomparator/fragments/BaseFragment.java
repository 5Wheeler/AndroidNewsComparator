package com.fivewheeler.training.newscomparator.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fivewheeler.training.newscomparator.AppController;
import com.fivewheeler.training.newscomparator.DashboardActivity;
import com.fivewheeler.training.newscomparator.OverlapDecoration;
import com.fivewheeler.training.newscomparator.R;
import com.fivewheeler.training.newscomparator.fragments.dummy.DummyContent;
import com.fivewheeler.training.newscomparator.fragments.dummy.DummyContent.DummyItem;
import com.fivewheeler.training.newscomparator.models.NewsJsonParseModel;
import com.fivewheeler.training.newscomparator.models.NewsXmlParseModel;
import com.fivewheeler.training.newscomparator.utils.SharedPreferencesHelper;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BaseFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    private List<String> mRssFeedUrls = new ArrayList<>();
    private String mGoogleFeedUrl;

    private List<DummyItem> mDummyItemList = new ArrayList<>();

    MyItemRecyclerViewAdapter myNewsAdapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BaseFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BaseFragment newInstance(int columnCount) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }

                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            });

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //mDummyItemList.clear();
        //getNewsFromFeeds();
    }

    public void setItems(List<DummyItem> items){
        mListener = (DashboardActivity) getActivity();
        myNewsAdapter = new MyItemRecyclerViewAdapter(items, mListener);
        recyclerView.setAdapter(myNewsAdapter);
        OverlapDecoration.vertOverlap = getVerticalMargin(380);
        recyclerView.addItemDecoration(new OverlapDecoration());
        ItemTouchHelper.Callback callback = new NewsItemTouchHelper(myNewsAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            getNewsFromFeeds();
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    /**
     * Request API to get the internation news from Rss Feed
     */
    private void getNewsFromFeeds(){

        if(this instanceof BusinessNewsFragment){
            //Put requests for all business urls

            mRssFeedUrls = new ArrayList<>(SharedPreferencesHelper.getInstance(getContext()).getBusinessNewsFeeds());

        }else if(this instanceof TechnologyNewsFragment){
            //Put requests for all business urls

            mRssFeedUrls = new ArrayList<>(SharedPreferencesHelper.getInstance(getContext()).getTechnologyNewsFeeds());

        }else if(this instanceof InternationalNewsFragment){
            //Put requests for all business urls

            mRssFeedUrls = new ArrayList<>(SharedPreferencesHelper.getInstance(getContext()).getInterNationalNewsFeeds());

        }else if(this instanceof NationalNewsFragment){
            //Put requests for all business urls

            mRssFeedUrls = new ArrayList<>(SharedPreferencesHelper.getInstance(getContext()).getNationalNewsFeeds());

        }else if(this instanceof HealthNewsFragment){
            //Put requests for all business urls

            mRssFeedUrls = new ArrayList<>(SharedPreferencesHelper.getInstance(getContext()).getHealthNewsFeeds());

        }else if(this instanceof EducationNewsFragment){
            //Put requests for all business urls

            mRssFeedUrls = new ArrayList<>(SharedPreferencesHelper.getInstance(getContext()).getEducationNewsFeeds());

        }else if(this instanceof SportsNewsFragment){
            //Put requests for all business urls

            mRssFeedUrls = new ArrayList<>(SharedPreferencesHelper.getInstance(getContext()).getSportsNewsFeeds());

        }else if(this instanceof AllNewsFragment){
            //Put requests for all business urls

            mRssFeedUrls = new ArrayList<>(SharedPreferencesHelper.getInstance(getContext()).getInterNationalNewsFeeds());
            mRssFeedUrls.addAll(SharedPreferencesHelper.getInstance(getContext()).getNationalNewsFeeds());
            mRssFeedUrls.addAll(SharedPreferencesHelper.getInstance(getContext()).getHealthNewsFeeds());
            mRssFeedUrls.addAll(SharedPreferencesHelper.getInstance(getContext()).getEducationNewsFeeds());
            mRssFeedUrls.addAll(SharedPreferencesHelper.getInstance(getContext()).getSportsNewsFeeds());
            mRssFeedUrls.addAll(SharedPreferencesHelper.getInstance(getContext()).getBusinessNewsFeeds());

        }


        for(int i=0; i < mRssFeedUrls.size(); i++) {
            if(mRssFeedUrls.get(i).contains("ajax.googleapis.com")){
                getNewsFromGoogleSearch(mRssFeedUrls.get(i));
            }

            StringRequest req = new StringRequest(Request.Method.GET, mRssFeedUrls.get(i), new Response.Listener<String>() {
                @Override
                public void onResponse(String xmlResponse) {
                    Log.i("XML RESPONSE", xmlResponse);

                    parseXmlDataWithSimpleXml(xmlResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });


            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(req, "get xml from rss feed");
        }


    }

    /**
     * This method is used to parse the xml response
     * @param xmlResponse
     * @return
     */
    private NewsXmlParseModel parseXmlDataWithSimpleXml(String xmlResponse){
        //Convert the xml to the list of model data required
        Reader reader = new StringReader(xmlResponse);
        Serializer serializer = new Persister();
        NewsXmlParseModel newsList = null;
        try {
            newsList =
                    serializer.read(NewsXmlParseModel.class, reader, false);
        }catch(Exception exception){
            Log.i("Exception", "parsed");

        }finally{
            Log.i("parse successfully", "parsed");

            //Convert news list and add to the list of dummy items'
            if(newsList==null || newsList.channel == null){
                return newsList;
            }
            for(int i = 0; i < newsList.channel.itemList.size(); i++){
                newsList.channel.itemList.get(i).title = newsList.channel.title + " : " + newsList.channel.itemList.get(i).title;
                DummyItem item = new DummyItem(newsList.channel.itemList.get(i).title,
                        newsList.channel.itemList.get(i).description,
                        newsList.channel.itemList.get(i).link);
                if(mDummyItemList.size() == 10){
                    setItems(mDummyItemList);
                    return newsList;
                }
                mDummyItemList.add(item);
            }

        }

        //TODO: Parse XML data using XmlPullParser
        // http://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html
        // http://www.tutorialspoint.com/android/android_xml_parsers.htm
        // http://www.androidhive.info/2011/11/android-xml-parsing-tutorial/
        setItems(mDummyItemList);
        return newsList;
    }

    /**
     * This method is used to do international news search from google
     */
    private void getNewsFromGoogleSearch(String url){
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url,new JSONObject(),new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject jsonResponse) {
                Log.i("JSON Response", "json response");

                //Convert Json response to the list of model data required
                parseJsonDataWithGson(jsonResponse.toString());

                //TODO: Parse the response using android Json library

                Log.i("Parsed Json", "json response is parsed to class object");
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, "get json from google feed");
    }

    private NewsJsonParseModel parseJsonDataWithGson(String jsonResponse){
        //Convert Json response to the list of model data required
        NewsJsonParseModel newsItem = new Gson().fromJson(jsonResponse, NewsJsonParseModel.class);

        if(newsItem.mResponseData == null){
            return null;
        }

        for(int i = 0; i < newsItem.mResponseData.mItemModel.size(); i++){
            newsItem.mResponseData.mItemModel.get(i).mTitle = "Google news : " + newsItem.mResponseData.mItemModel.get(i).mTitle;
            DummyItem item = new DummyItem(newsItem.mResponseData.mItemModel.get(i).mTitle,
                    newsItem.mResponseData.mItemModel.get(i).mContent,
                    newsItem.mResponseData.mItemModel.get(i).mUrl);
            mDummyItemList.add(item);
        }

        setItems(mDummyItemList);

        return newsItem;

    }

    public class NewsItemTouchHelper extends ItemTouchHelper.SimpleCallback {
        private MyItemRecyclerViewAdapter mNewsAdapter;

        public NewsItemTouchHelper(MyItemRecyclerViewAdapter newsAdapter){

            super(0, 0);
            this.mNewsAdapter = newsAdapter;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //TODO: Not implemented here
            return true;
        }

        @Override
        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //Remove item
            int myDirection = direction;
            mDummyItemList.remove(viewHolder.getAdapterPosition());
            //mNewsAdapter.notifyDataSetChanged();
            mNewsAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            final int swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int dragFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }


    public int getVerticalMargin(int heightInPx){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = - (int) (heightInPx * ( (float) metrics.densityDpi/DisplayMetrics.DENSITY_DEFAULT));
        return height;

    }



}
