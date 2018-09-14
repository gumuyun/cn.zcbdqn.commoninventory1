package cn.zcbdqn.commoninventory.context;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TabHost;

import com.pow.api.cls.RfidPower;
import com.uhf.api.cls.Reader;
import com.uhf.api.cls.Reader.TAGINFO;
import com.uhf.uhf.UHF1.UHF1Application;
import com.uhf.uhf.UHF1.UHF1Function.SPconfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class MyApplication extends UHF1Application {

	private static Stack<Activity> activityStack;
	private List<Activity> activityList = new ArrayList<Activity>();
	private static MyApplication singleton;
	public final static String CONF_FRIST_START = "isFristStart";
	public static boolean flag = false;
	public static Context mContext;

	public static Map<String,Object> applicationMap=new HashMap<String, Object>();
	// 单例模式,加锁
	public synchronized static MyApplication getInstance() {

		if (singleton == null) {
			singleton = new MyApplication();
		}
		return singleton;
	}


	public void setFileMapstate(boolean flag) {
		
	}

	public void setFileMaplive(boolean flag) {
		
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this.getApplicationContext();
//		AppExceptionUncatch.getInstance().init(mContext);
		/*try {
			ReaderHelper.setContext(getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		singleton = this;
		// Thread.currentThread().setUncaughtExceptionHandler(MyCrashHandler.getInstance(getApplicationContext()));
		// loggerM=LoggerManager.getLoggerInstance();
		// providertool tool = new providertool(getApplicationContext());
		// tool.cleandatabase();
		// if (fileMap.isEmpty())
		// {
		// filemaple = new HashMap<String, downinfo>();
		// filemaple = tool.getallinfo();
		// Iterator it = filemaple.keySet().iterator();
		//
		// while (it.hasNext()){
		// String key;
		// key=(String)it.next();
		// fileMap.put(key, filemaple.get(key));
		//
		// }
		//
		//
		// }
	}

/*	public Map<String, downinfo> cleanmap() {
		ArrayList<String> delMap = new ArrayList<String>();
		Iterator it = fileMap.keySet().iterator();
		while (it.hasNext()) {
			String key;
			key = (String) it.next();
			// downResult.add(filemap.get(key));
			FileUtility flie = new FileUtility();
			if (!flie.isFileExist(fileMap.get(key).filename,
					DrugSystemConst.download_dir)) {
				// fileMap.remove(key);
				// delMap.put(key, fileMap.get(key));
				delMap.add(key);
			}

		}
		for (int i = 0; i < delMap.size(); i++) {
			fileMap.remove(delMap.get(i));
		}
		return fileMap;
	}*/

	/**
	 * add Activity
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * get current Activity
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
     * 
     */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
     * 
     */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
     * 
     */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
     *
     */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
     * 
     */
	public void AppExit() {
		try {
			finishAllActivity();
		} catch (Exception e) {
		}
	}

	/**
	 * 获取App安装包信息
	 *
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	public List<Activity> getActivityList() {
		if (activityList != null) {
			return activityList;
		}
		return activityList;
	}

	public void delActivityList(Activity activity) {
		if (activityList != null) {
			activity.finish();
			activityList.remove(activity);
			activity = null;
		}
	}

	public void AddActivity(Activity activity) {

		if (activity != null) {
			activityList.add(activity);
		}
	}

	// 彻底退出程序
	public void exitActivity() {
		try {

			for (Activity activity : activityList) {
				if (activity != null) {
					activity.finish();
				}
			}

			android.os.Process.killProcess(android.os.Process.myPid());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			System.gc();
			System.exit(0);
		}
	}

	//---u1,u2,parameters
	//u1,u2
    public Map<String,TAGINFO> Devaddrs=new LinkedHashMap<String,TAGINFO>();//鏈夊簭
	public String path;
    public int ThreadMODE=0;
	public int refreshtime=1000;
	public int Mode;
	public Map<String, String> m;
	public TabHost tabHost;
	public long exittime;
	public boolean needreconnect;
    public Reader Mreader;
    public int antportc;
    public String Curepc;
	public int Bank;
	public int BackResult;
	
    public SPconfig spf;
	public RfidPower Rpower;

	
	public ReaderParams Rparams;
	public String Address;
	public boolean nostop=false;
	public class ReaderParams{
		//save param
		public int opant;
		
		public List<String> invpro;
		public String opro;
		public int[] uants;
		public int readtime;
		public int sleep;

		public int checkant;
		public int[] rpow;
		public int[] wpow;
		
		public int region;
		public int[] frecys;
		public int frelen;
		
		public int session;
		public int qv;
		public int wmode;
		public int blf;
		public int maxlen;
		public int target;
		public int gen2code;
		public int gen2tari;
		
		public String fildata;
		public int filadr;
		public int filbank;
		public int filisinver;
		public int filenable;
		
		public int emdadr;
		public int emdbytec;
		public int emdbank;
		public int emdenable;
		
		public int antq;
		public int adataq;
		public int rhssi;
		public int invw;
		public int iso6bdeep;
		public int iso6bdel;
		public int iso6bblf;
		public int option;
		//other params
		
		public String password;
		public int optime;
		public ReaderParams()
		{
			opant=1;
			invpro=new ArrayList<String>();
			invpro.add("GEN2");
			uants=new int[1];
			uants[0]=1;
			sleep=0;
			readtime=50;
			optime=1000;
			opro="GEN2";
			checkant=1;
			rpow=new int[]{2700,2000,2000,2000};
			wpow=new int[]{2000,2000,2000,2000};
			region=1;
			frelen=0; 
			session=0; 
			qv=-1; 
			wmode=0; 
			blf=0; 
			maxlen=0; 
			target=0; 
			gen2code=2; 
			gen2tari=0; 
	 
			fildata="";
			filadr=32;
			filbank=1;
			filisinver=0;
			filenable=0; 
			
			emdadr=0;
			emdbytec=0; 
			emdbank=1;
			emdenable=0; 
			 
			adataq=0; 
			rhssi=1; 
			invw=0; 
			iso6bdeep=0; 
			iso6bdel=0; 
			iso6bblf=0;
			option=0;
		}
	}
	//---end-u1,u2,parameters
}
