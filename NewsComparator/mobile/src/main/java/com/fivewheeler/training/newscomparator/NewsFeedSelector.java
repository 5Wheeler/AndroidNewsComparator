package com.fivewheeler.training.newscomparator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fivewheeler.training.newscomparator.models.NewsJsonParseModel;
import com.fivewheeler.training.newscomparator.models.NewsXmlParseModel;
import com.fivewheeler.training.newscomparator.utils.SharedPreferencesHelper;
import com.fivewheeler.training.newscomparator.views.MultiSelectionSpinner;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * This activity will show you a selector screen.
 * User will be able to select a list of feeds for different types of news categories that he is interested in.
 * - InterNational news feeds from popular newspapers
 * - InterNational news feeds from popular news channels
 * - National news feeds from popular newspapers
 * - National news feeds from popular news channels
 * - Technology news from news papers
 * - Technology news from news channels
 * - Business news from news papers
 * - Business news from news channels
 * - Sports news from news papers
 * - Sports news from news channels
 * - Education news from news papers
 * - Education news from news channels
 * - Health news from news papers
 * - Health news from news channels
 *
 * Version1 of application:
 * - Currently we are having single selector to choose the desired Newspapers
 * - UI can be updated to have multiple selectors for all the feeds.
 * - Now these feed links selected are saved in the preferences (Later extend them to database and use content providers)
 *
 */
public class NewsFeedSelector extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * News papers supporting the regular RSS feeds
     * Refer to the news item model class, to see the model data supported
     * Testing push
     */
    Button button;
    private ProgressDialog prgrs;


    private String[] mNewspaperAndNewsChannelsList = {
            "Hindu",
            "Tribune",
            "Trending google news"
    };

    /**
     * Google search can only be used for User prompted searches.
     * Nobody is allowed to do defaulted search.
     */
    private final String GOOGLE_SEARCH_BASE_JSON_URL = "https://ajax.googleapis.com/ajax/services/search/news?v=1.0&q=";


    MultiSelectionSpinner mInternationalSpinner;

    //Last item in every list is a trending google news
    private String[] mInterNationalNewsFeedList = {
            "http://www.thehindu.com/news/international/?service=rss",
            "http://www.tribuneindia.com/rss/feed.aspx?cat_id=8",
            "https://json..google.com/news?hl=en&output=rss&topic=International%20News"
    };

    private String[] mSelectedInterNationalNewsFeedList;

    MultiSelectionSpinner mNationalSpinner;

    private String[] mNationalNewsFeedList = {
            "http://www.thehindu.com/news/national/?service=rss",
            "http://www.tribuneindia.com/rss/feed.aspx?cat_id=7",
            "https://json.google.com/news?hl=en&output=rss&topic=Latest%20Indian%20National%20News"
    };

    private String[] mSelectedNationalNewsFeedList;

    MultiSelectionSpinner mTechnologySpinner;

    private String[] mTechnologyNewsFeedList = {
            "http://www.thehindu.com/news/technology/?service=rss",
            "http://www.tribuneindia.com/rss/feed.aspx?cat_id=18",
            "https://json.google.com/news?hl=en&output=rss&topic=Latest%20Technology%20News"
    };

    private String[] mSelectedTechnologyNewsFeedList;

    MultiSelectionSpinner mBusinessSpinner;

    private String[] mBusinessNewsFeedList = {
            "http://www.thehindu.com/news/business/?service=rss",
            "http://www.tribuneindia.com/rss/feed.aspx?cat_id=10",
            "https://json.google.com/news?hl=en&output=rss&topic=Latest%20Business%20News"
    };

    private String[] mSelectedBusinessNewsFeedList;

    MultiSelectionSpinner mSportsSpinner;

    private String[] mSportsNewsFeedList = {
            "http://www.thehindu.com/news/sports/?service=rss",
            "http://www.tribuneindia.com/rss/feed.aspx?cat_id=9",
            "https://json.google.com/news?hl=en&output=rss&topic=Latest%20Indian%20Sports%20News"
    };

    private String[] mSelectedSportsNewsFeedList;

    MultiSelectionSpinner mEducationSpinner;

    //"https://news.google.com/news?hl=en&output=rss&topic=Latest%20Indian%20Education%20News"
    private String[] mEducationNewsFeedList = {
            "http://www.thehindu.com/news/education/?service=rss",
            "http://www.tribuneindia.com/rss/feed.aspx?cat_id=90",
            "https://json.google.com/news?hl=en&output=rss&topic=Latest%20Indian%20Education%20News"
    };

    private String[] mSelectedEducationNewsFeedList;

    MultiSelectionSpinner mHealthSpinner;

    private String[] mHealthNewsFeedList = {
            "http://www.thehindu.com/news/health/?service=rss",
            "http://www.tribuneindia.com/rss/feed.aspx?cat_id=19",
            "https://json.google.com/news?hl=en&output=rss&topic=Latest%20Health%20News"
    };

    private String[] mSelectedHealthNewsFeedList;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_selector);

        if(!SharedPreferencesHelper.getInstance(this).isFirstTimeLaunch()){
            finish();
            startActivity(new Intent(this, DashboardActivity.class));
            return;
        }

        button = (Button) findViewById(R.id.continueButton);
        initView();


        // Spinner element
        mInternationalSpinner= (MultiSelectionSpinner ) findViewById(R.id.internationalNewsFeedSelector);

        // Spinner click listener
        mInternationalSpinner.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        mInternationalSpinner.setItems(mNewspaperAndNewsChannelsList);
        mInternationalSpinner.setSelection(new int[]{0, 1, 2});


        // Spinner element
        mNationalSpinner = (MultiSelectionSpinner) findViewById(R.id.nationalNewsFeedSelector);

        // Spinner click listener
        mNationalSpinner.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        mNationalSpinner.setItems(mNewspaperAndNewsChannelsList);
        mNationalSpinner.setSelection(new int[]{0, 1, 2});


        // Spinner element
        mEducationSpinner = (MultiSelectionSpinner) findViewById(R.id.educationNewsFeedSelector);

        // Spinner click listener
        mEducationSpinner.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        mEducationSpinner.setItems(mNewspaperAndNewsChannelsList);
        mEducationSpinner.setSelection(new int[]{0, 1, 2});




        // Spinner element
        mSportsSpinner = (MultiSelectionSpinner) findViewById(R.id.sportsNewsFeedSelector);

        // Spinner click listener
        mSportsSpinner.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        mSportsSpinner.setItems(mNewspaperAndNewsChannelsList);
        mSportsSpinner.setSelection(new int[]{0, 1, 2});



        // Spinner element
        mBusinessSpinner = (MultiSelectionSpinner) findViewById(R.id.businesssNewsFeedSelector);

        // Spinner click listener
        mBusinessSpinner.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        mBusinessSpinner.setItems(mNewspaperAndNewsChannelsList);
        mBusinessSpinner.setSelection(new int[]{0, 1, 2});



        // Spinner element
        mTechnologySpinner = (MultiSelectionSpinner) findViewById(R.id.technologyNewsFeedSelector);

        // Spinner click listener
        mTechnologySpinner.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        mTechnologySpinner.setItems(mNewspaperAndNewsChannelsList);
        mTechnologySpinner.setSelection(new int[]{0, 1, 2});


        // Spinner element
        mHealthSpinner = (MultiSelectionSpinner) findViewById(R.id.healthNewsFeedSelector);

        // Spinner click listener
        mHealthSpinner.setOnItemSelectedListener(this);


        // attaching data adapter to spinner
        mHealthSpinner.setItems(mNewspaperAndNewsChannelsList);
        mHealthSpinner.setSelection(new int[]{0, 1, 2});


        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    prgrs=new ProgressDialog(NewsFeedSelector.this);
                    prgrs.setMessage("Retrieving News");
                    prgrs.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    prgrs.setIndeterminate(true);
                    prgrs.setProgress(0);
                    prgrs.show();

                    final int totalProgressTime = 500;
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            int jumpTime = 0;

                            while(jumpTime < totalProgressTime) {
                                try {
                                    sleep(100);
                                    jumpTime += 15;
                                    prgrs.setProgress(jumpTime);
                                }
                                catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    t.start();




                //Save the current feeds selected in preferences
                mSelectedInterNationalNewsFeedList = new String[mInternationalSpinner.getSelectedIndices().size()];
                for(int i = 0; i < mInternationalSpinner.getSelectedIndices().size(); i++){
                    mSelectedInterNationalNewsFeedList[i] = mInterNationalNewsFeedList[mInternationalSpinner.getSelectedIndices().get(i)];
                }
                SharedPreferencesHelper.getInstance(NewsFeedSelector.this)
                        .setInterNationalNewsFeeds(new HashSet<String>(Arrays.asList(mSelectedInterNationalNewsFeedList)));

                mSelectedNationalNewsFeedList = new String[mNationalSpinner.getSelectedIndices().size()];
                for(int i = 0; i < mNationalSpinner.getSelectedIndices().size(); i++){
                    mSelectedNationalNewsFeedList[i] = mNationalNewsFeedList[mNationalSpinner.getSelectedIndices().get(i)];
                }
                SharedPreferencesHelper.getInstance(NewsFeedSelector.this)
                        .setNationalNewsFeeds(new HashSet<String>(Arrays.asList(mSelectedNationalNewsFeedList)));

                mSelectedBusinessNewsFeedList = new String[mBusinessSpinner.getSelectedIndices().size()];
                for(int i = 0; i < mBusinessSpinner.getSelectedIndices().size(); i++){
                    mSelectedBusinessNewsFeedList[i] = mBusinessNewsFeedList[mBusinessSpinner.getSelectedIndices().get(i)];
                }
                SharedPreferencesHelper.getInstance(NewsFeedSelector.this)
                        .setBusinessNewsFeeds(new HashSet<String>(Arrays.asList(mSelectedBusinessNewsFeedList)));

                mSelectedTechnologyNewsFeedList = new String[mTechnologySpinner.getSelectedIndices().size()];
                for(int i = 0; i < mTechnologySpinner.getSelectedIndices().size(); i++){
                    mSelectedTechnologyNewsFeedList[i] = mTechnologyNewsFeedList[mTechnologySpinner.getSelectedIndices().get(i)];
                }
                SharedPreferencesHelper.getInstance(NewsFeedSelector.this)
                        .setTechnologyNewsFeeds(new HashSet<String>(Arrays.asList(mSelectedTechnologyNewsFeedList)));

                mSelectedEducationNewsFeedList = new String[mEducationSpinner.getSelectedIndices().size()];
                for(int i = 0; i < mEducationSpinner.getSelectedIndices().size(); i++){
                    mSelectedEducationNewsFeedList[i] = mEducationNewsFeedList[mEducationSpinner.getSelectedIndices().get(i)];
                }
                SharedPreferencesHelper.getInstance(NewsFeedSelector.this)
                        .setEducationNewsFeeds(new HashSet<String>(Arrays.asList(mSelectedEducationNewsFeedList)));

                mSelectedHealthNewsFeedList = new String[mHealthSpinner.getSelectedIndices().size()];
                for(int i = 0; i < mHealthSpinner.getSelectedIndices().size(); i++){
                    mSelectedHealthNewsFeedList[i] = mHealthNewsFeedList[mHealthSpinner.getSelectedIndices().get(i)];
                }

                mSelectedSportsNewsFeedList = new String[mSportsSpinner.getSelectedIndices().size()];
                for(int i = 0; i < mSportsSpinner.getSelectedIndices().size(); i++){
                    mSelectedSportsNewsFeedList[i] = mSportsNewsFeedList[mSportsSpinner.getSelectedIndices().get(i)];
                }
                SharedPreferencesHelper.getInstance(NewsFeedSelector.this)
                        .setHealthNewsFeeds(new HashSet<String>(Arrays.asList(mSelectedHealthNewsFeedList)));


                //SharedPreferencesHelper.getInstance(NewsFeedSelector.this).setFeedSelections();
                SharedPreferencesHelper.getInstance(NewsFeedSelector.this).setFirstLaunch(false);
                //Launch the home screen for news, with selected feeds
                startActivity(new Intent(NewsFeedSelector.this, DashboardActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                finish();
            }
        });


    }



    /**
     * This view is used to initialize all the UI elements loaded from the xml
     */
    private void initView(){

        //Testing APIs
       // getInternationalNewsFromRssFeed();
        //getInternationalNewsFromGoogleSearch();

    }

    /**
     * Request API to get the internation news from Rss Feed
     */
    private void getInternationalNewsFromRssFeed(){
        String url = "http://www.thehindu.com/news/international/?service=rss";
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String xmlResponse) {
                Log.i("XML RESPONSE", xmlResponse);

                parseXmlDataWithSimpleXml(xmlResponse);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, "get xml from rss feed");
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
        }

        //TODO: Parse XML data using XmlPullParser
        // http://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html
        // http://www.tutorialspoint.com/android/android_xml_parsers.htm
        // http://www.androidhive.info/2011/11/android-xml-parsing-tutorial/
        return newsList;
    }


    /**
     * This method is used to do international news search from google
     */
    private void getInternationalNewsFromGoogleSearch(){
        String url = "https://ajax.googleapis.com/ajax/services/search/news?v=1.0&q=narendra%20modi";
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
        return new Gson().fromJson(jsonResponse, NewsJsonParseModel.class);

    }


    /**
     * This method is used to test parsing xml data
     * Parse XML using XMLPullParser
     *
     * Exercise to complete
     */
    private void parseXMlDataWithXmlParserAndHttpConnection(){

    }


    /**
     * This method is used to test parsing json data
     *
     * Exercise to complete
     */
    private void parseJsonDataWithJsonLibraryAndHttpConnection(){

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
