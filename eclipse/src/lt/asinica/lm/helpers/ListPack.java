package lt.asinica.lm.helpers;

import lt.asinica.lm.objects.Search;

public class ListPack {
	Torrents mTorrents;
	Search mSearch;
	int mPage;
	String mTitle;
	boolean mEverythingLoaded;
	boolean mLoadingMore;
	
	public Torrents getTorrents() { return mTorrents; }
	public Search getSearch() { return mSearch; }
	public int getPage() { return mPage; }
	public String getTitle() { return mTitle; }
	public boolean getEverythingLoaded() { return mEverythingLoaded; }
	public boolean getLoadingMore() { return mLoadingMore; }
	
	public ListPack(Torrents torrents, Search search, int page, String title, boolean everythingLoaded, boolean loadingMore) {
		mTorrents = torrents;
		mSearch = search;
		mPage = page;
		mTitle = title;
		mEverythingLoaded = everythingLoaded;
		mLoadingMore = loadingMore;
	}
	
}