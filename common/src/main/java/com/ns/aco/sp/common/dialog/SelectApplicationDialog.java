package com.ns.aco.sp.common.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ns.aco.sp.common.R;
import com.ns.aco.sp.common.extent.MyArrayAdapterResolveInfo;

import java.io.File;
import java.util.List;

public abstract class SelectApplicationDialog extends DialogFragment {

	protected static File _lastFile = null; // 前回選択されたファイルを保持
	private ListView _listApps = null;
	private List<ResolveInfo> _selectedPackageList = null;
	private Button _Ok = null;
	private Button _Cancel = null;

	protected abstract String dialogTitle();
	protected abstract void okClick(ListView listView);
	protected abstract void cancelClick();

	public void setSelectedPackageList(List<ResolveInfo> selectedPackageList){
		_selectedPackageList = selectedPackageList;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View root = inflater.inflate(R.layout.dialog_select_multi_file, null);

		_listApps = (ListView) root.findViewById(R.id.listApps);

		_Ok = (Button) root.findViewById(R.id.fileListOk);
		_Ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				okClick(_listApps);
			}
		});

		_Cancel = (Button) root.findViewById(R.id.fileListCancel);
		_Cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cancelClick();
			}
		});

		setListviewItems();

		builder.setView(root);
		builder.setTitle(dialogTitle());
		this.setCancelable(false);
		return builder.create();
	}

	private void setListviewItems(){
		PackageManager packageManager = getActivity().getPackageManager();
		// ランチャーから起動出来るアプリケーションの一覧
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
		MyArrayAdapterResolveInfo arrayAdapter = new MyArrayAdapterResolveInfo(
				getActivity(), R.layout.listview_check_item, resolveInfoList, packageManager);
		if (_selectedPackageList != null && _selectedPackageList.size() > 0){
			arrayAdapter.setSelectedPackageList(_selectedPackageList);
		}
		_listApps.setAdapter(arrayAdapter);
	}
};
