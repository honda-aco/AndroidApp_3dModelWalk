package com.ns.aco.sp.common.dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.ns.aco.sp.common.R;
import com.ns.aco.sp.common.extent.MyArrayAdapterFile;

public abstract class SelectFileDialog extends DialogFragment {

	protected static File _lastFile = null; // 前回選択されたファイルを保持
	private ListView _listDirectories = null;
	private Button _Cancel = null;
	private File _rootDirectory = null;
	private File _parentDirectiory = null;
	private final int READ_EXTERNAL_STORAGE = 0;

	protected abstract String dialogTitle();
	protected abstract void fileClick(File file);

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View root = inflater.inflate(R.layout.dialog_select_file, null);

		_listDirectories = (ListView) root.findViewById(R.id.listDirectories);
		_listDirectories.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem((ListView)parent, position);
			}
		});

		_Cancel = (Button) root.findViewById(R.id.fileListCancel);
		_Cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		// ストレージアクセス権限確認用ダイアログを表示
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE);
		}

		builder.setView(root);
		builder.setTitle(dialogTitle());
		this.setCancelable(false);
		return builder.create();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
			return;
		}

		switch (requestCode) {
			// 先ほどの独自定義したrequestCodeの結果確認
			case READ_EXTERNAL_STORAGE:
				// 前回選択したファイルのパスを表示する
				File defaultFile = null;
				if (_lastFile != null){
					defaultFile = _lastFile.getParentFile();
				}else{
					defaultFile = Environment.getExternalStorageDirectory();
				}
				_rootDirectory = defaultFile;
				setListviewDirectories(defaultFile);
				break;
		}
	}

	private void selectItem(ListView parent, int position){
		File file = (File)parent.getItemAtPosition(position);

		if(file.isDirectory()){
			if (_parentDirectiory == file){
				file = file.getParentFile();
			}
			_parentDirectiory = null;
			setListviewDirectories(file);
		}else{
			fileClick(file);
		}
	}

    private void setListviewDirectories(File file){
		File files[] = file.listFiles();
		MyArrayAdapterFile directoriesArray = null;

		if (files == null){
			// 空のディレクトリ配下を表示
			List<File> array = new ArrayList<>();
			array.add(file);
			directoriesArray = new MyArrayAdapterFile(getActivity(), R.layout.listview_item, array, file);
		}else if (file.getAbsolutePath().equals(_rootDirectory.getAbsolutePath())){
			// ルートディレクトリ配下を表示
			directoriesArray = new MyArrayAdapterFile(getActivity(), R.layout.listview_item, Arrays.asList(files));
		}else{
			// ルートディレクトリ以外のディレクトリ配下を表示
			_parentDirectiory = file;
			List<File> array = new ArrayList<>();
			array.add(file);
			for (File item : files) {
				array.add(item);
			}
			directoriesArray = new MyArrayAdapterFile(getActivity(), R.layout.listview_item, array, file);
		}
		_listDirectories.setAdapter(directoriesArray);
    };
};
