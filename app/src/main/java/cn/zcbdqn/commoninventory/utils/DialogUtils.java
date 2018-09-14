package cn.zcbdqn.commoninventory.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class DialogUtils {

    public static AlertDialog.Builder dialogBuilder(Context context, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (msg != null) builder.setMessage(msg);
        if (title != null) builder.setTitle(title);
        return builder;
    }

    public static AlertDialog.Builder dialogBuilder(Context context, String title, String msg, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (msg != null) builder.setMessage(Html.fromHtml(msg));
        if (title != null) builder.setTitle(title);
        return builder;
    }


    public static AlertDialog.Builder dialogBuilder(Context context, int title, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (view != null) builder.setView(view);
        if (title > 0) builder.setTitle(title);
        return builder;
    }

    public static AlertDialog.Builder dialogBuilder(Context context, int titleResId, int msgResId) {
        String title = titleResId > 0 ? context.getResources().getString(titleResId) : null;
        String msg = msgResId > 0 ? context.getResources().getString(msgResId) : null;
        return dialogBuilder(context, title, msg);
    }

    public static Dialog showTips(Context context, String title, String des) {
        AlertDialog.Builder builder = DialogUtils.dialogBuilder(context, title, des);
        builder.setCancelable(true);
        Dialog dialog = builder.show();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog showTips(Context context, int title, int des) {
        return showTips(context, context.getString(title), context.getString(des));
    }
    
    /**
	 * ������ͨ����ť�Ի���
	 * 
	 * @param ctx
	 *            ������ ����
	 * @param iconId
	 *            ͼ�꣬�磺R.drawable.icon ����
	 * @param title
	 *            ���� ����
	 * @param message
	 *            ��ʾ���� ����
	 * @param btnName
	 *            ��ť���� ����
	 * @param listener
	 *            ����������ʵ��android.content.DialogInterface.OnClickListener�ӿ� ����
	 * @return
	 */
	public static Dialog createDialog(Context ctx, int iconId, String title,
			String message, String btnName,
			android.content.DialogInterface.OnClickListener listener) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ctx);
		// ���öԻ����ͼ��
		builder.setIcon(iconId);
		// ���öԻ���ı���
		builder.setTitle(title);
		// ���öԻ������ʾ����
		builder.setMessage(message);
		// ��Ӱ�ť��android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setPositiveButton(btnName, listener);
		// ����һ����ͨ�Ի���
		dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}

	/**
	 * ������ͨ˫��ť�Ի���
	 * 
	 * @param ctx
	 *            ������ ����
	 * @param iconId
	 *            ͼ�꣬�磺R.drawable.icon[������ʾ��д0] ����
	 * @param title
	 *            ���� ����
	 * @param message
	 *            ��ʾ���� ����
	 * @param btnPositiveName
	 *            ��һ����ť���� ����
	 * @param listener_Positive
	 *            ��һ������������ʵ��android.content.DialogInterface.OnClickListener�ӿ� ����
	 * @param btnNegativeName
	 *            �ڶ�����ť���� ����
	 * @param listener_Negative
	 *            �ڶ�������������ʵ��android.content.DialogInterface.OnClickListener�ӿ� ����
	 * @return �Ի���ʵ��
	 */
	public static Dialog createDialog(Context ctx, int iconId, String title,
			String message, String btnPositiveName,
			android.content.DialogInterface.OnClickListener listener_Positive,
			String btnNegativeName,
			android.content.DialogInterface.OnClickListener listener_Negative) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ctx);
		// ���öԻ����ͼ��
		builder.setIcon(iconId);
		// ���öԻ���ı���
		builder.setTitle(title);
		// ���öԻ������ʾ����
		builder.setMessage(message);
		// ��Ӱ�ť��android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setPositiveButton(btnPositiveName, listener_Positive);
		// ��Ӱ�ť��android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setNegativeButton(btnNegativeName, listener_Negative);
		// ����һ����ͨ�Ի���
		dialog = builder.create();
		return dialog;
	}

	/**
	 * �����б�Ի���
	 * 
	 * @param ctx
	 *            ������ ����
	 * @param iconId
	 *            ͼ�꣬�磺R.drawable.icon ����
	 * @param title
	 *            ���� ����
	 * @param itemsId
	 *            �ַ���������Դid ����
	 * @param listener
	 *            ����������ʵ��android.content.DialogInterface.OnClickListener�ӿ� ����
	 * @return
	 */
	public static Dialog createListDialog(Context ctx, int iconId,
			String title, int itemsId,
			android.content.DialogInterface.OnClickListener listener) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		// ���öԻ����ͼ��
		builder.setIcon(iconId);
		// ���öԻ���ı���
		builder.setTitle(title);
		// ��Ӱ�ť��android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setItems(itemsId, listener);
        builder.setCancelable(false);
		// ����һ���б�Ի���
		dialog = builder.create();
		return dialog;
	}

	/**
	 * ������ѡ��ť�Ի���
	 * 
	 * @param ctx
	 *            ������ ����
	 * @param iconId
	 *            ͼ�꣬�磺R.drawable.icon ����
	 * @param title
	 *            ���� ����
	 * @param itemsId
	 *            �ַ���������Դid ����
	 * @param listener
	 *            ��ѡ��ť�����������ʵ��android.content.DialogInterface.OnClickListener�ӿ�
	 *            ����
	 * @param btnName
	 *            ��ť���� ����
	 * @param listener2
	 *            ��ť����������ʵ��android.content.DialogInterface.OnClickListener�ӿ� ����
	 * @return
	 */
	public static Dialog createRadioDialog(Context ctx, int iconId,
			String title, int itemsId,
			android.content.DialogInterface.OnClickListener listener,
			String btnName,
			android.content.DialogInterface.OnClickListener listener2) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ctx);
		// ���öԻ����ͼ��
		builder.setIcon(iconId);
		// ���öԻ���ı���
		builder.setTitle(title);
		// 0: Ĭ�ϵ�һ����ѡ��ť��ѡ��
		builder.setSingleChoiceItems(itemsId, 0, listener);
		// ���һ����ť
		builder.setPositiveButton(btnName, listener2);
		// ����һ����ѡ��ť�Ի���
		dialog = builder.create();
		return dialog;
	}

	/**
	 * ������ѡ�Ի���
	 * 
	 * @param ctx
	 *            ������ ����
	 * @param iconId
	 *            ͼ�꣬�磺R.drawable.icon ����
	 * @param title
	 *            ���� ����
	 * @param itemsId
	 *            �ַ���������Դid ����
	 * @param flags
	 *            ��ʼ��ѡ��� ����
	 * @param listener
	 *            ��ѡ��ť�����������ʵ��android.content.DialogInterface.
	 *            OnMultiChoiceClickListener�ӿ� ����
	 * @param btnName
	 *            ��ť���� ����
	 * @param listener2
	 *            ��ť����������ʵ��android.content.DialogInterface.OnClickListener�ӿ� ����
	 * @return
	 */
	public static Dialog createCheckBoxDialog(
			Context ctx,
			int iconId,
			String title,
			int itemsId,
			boolean[] flags,
			android.content.DialogInterface.OnMultiChoiceClickListener listener,
			String btnName,
			android.content.DialogInterface.OnClickListener listener2) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ctx);
		// ���öԻ����ͼ��
		builder.setIcon(iconId);
		// ���öԻ���ı���
		builder.setTitle(title);
		builder.setMultiChoiceItems(itemsId, flags, listener);
		// ���һ����ť
		builder.setPositiveButton(btnName, listener2);
		// ����һ����ѡ�Ի���
		dialog = builder.create();
		return dialog;
	}

	/**
	 * ���ڶԻ���
	 * 
	 * @param context
	 *            ������
	 * @param v
	 *            ��.setText�Ŀؼ�
	 * @return �Ի���ʵ��
	 */
	public static Dialog createDateDialog(Context context, final View v) {
		Dialog dialog = null;
		Calendar calender = Calendar.getInstance();
		dialog = new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {

						if (v instanceof TextView) {

							((TextView) v).setText(year + "��"
									+ (monthOfYear + 1) + "��" + dayOfMonth
									+ "��");
						}
						if (v instanceof EditText) {
							((EditText) v).setText(year + "��"
									+ (monthOfYear + 1) + "��" + dayOfMonth
									+ "��");
						}

					}
				}, calender.get(calender.YEAR), calender.get(calender.MONTH),
				calender.get(calender.DAY_OF_MONTH));

		return dialog;
	}

	/**
	 * ���ضԻ���
	 * 
	 * @param context
	 *            ������
	 * @param msg
	 *            ����
	 * @return
	 */
	public static ProgressDialog createLoadDialog(Context context, String msg) {
		ProgressDialog dialog = new ProgressDialog(context);
		// ���÷��ΪԲ�ν�����
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// ��������
		dialog.setMessage(msg);
		// ���ý������Ƿ�Ϊ����ȷ
		dialog.setIndeterminate(false);
		// ���ÿհ׳����ر�
		dialog.setCanceledOnTouchOutside(false);
		// ���ý������Ƿ���԰��˻ؼ�ȡ��
		dialog.setCancelable(true);
		return dialog;
	}
}
