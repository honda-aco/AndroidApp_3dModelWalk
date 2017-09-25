package com.ns.aco.sp.dialog;

import java.io.File;

import android.widget.Toast;

import com.ns.aco.sp.R;
import com.ns.aco.sp.common.dialog.SelectFileDialog;

public class SelectPngFileDialog extends SelectFileDialog implements DialogContract.SelectPngFile {

	private Presenter _presenter;

	@Override
	protected String dialogTitle() {
		return null;
	}

	@Override
	protected void fileClick(File file) {
		String extension = "png";
	    int index = file.getName().lastIndexOf(".");

		if (index != -1 && file.getName().substring(index + 1).toLowerCase().equals(extension.toLowerCase())){
			_lastFile = file;
			_presenter.result_selectPngFile(file);
			dismiss();
		}else{
			// アラートダイアログ を生成
			Toast.makeText(getActivity(), getActivity().getString(R.string.value0029), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		_presenter = presenter;
	}
};