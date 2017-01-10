package com.fwk.school4.ui;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.Toast;

import com.fwk.school4.utils.DataConversionUtils;


public abstract class NFCBaseActivity extends BasaActivity {
	private NfcAdapter nfcAdapter = null;
	private PendingIntent pendingIntent = null;
	private IntentFilter[] intentFilters = null;
	private String[][] techList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获取默认的NFC控制器
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter == null) {
			Toast.makeText(this, "设备不支持NFC功能，请更换设备！", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (!nfcAdapter.isEnabled()) {
			Toast.makeText(this, "请在系统设置中先启用NFC功能！", Toast.LENGTH_SHORT).show();
		}
		pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		IntentFilter ndefFilter = new IntentFilter(
				NfcAdapter.ACTION_NDEF_DISCOVERED);
		IntentFilter techFilter = new IntentFilter(
				NfcAdapter.ACTION_TECH_DISCOVERED);
		intentFilters = new IntentFilter[] { ndefFilter, techFilter };
		techList = new String[][] { new String[] { "android.nfc.tech.IsoDep" },
				new String[] { "android.nfc.tech.NfcA" },
				new String[] { "android.nfc.tech.NfcB" },
				new String[] { "android.nfc.tech.NfcF" },
				new String[] { "android.nfc.tech.NfcV" },
				new String[] { "android.nfc.tech.Ndef" },
				new String[] { "android.nfc.tech.NdefFormatable" },
				new String[] { "android.nfc.tech.MifareClassic" },
				new String[] { "android.nfc.tech.MifareUltralight" } };
	}

	@Override
	protected void onResume() {
		if (nfcAdapter != null)
			nfcAdapter.enableForegroundDispatch(this, pendingIntent,
					intentFilters, techList);
		super.onResume();
	}

	@Override
	protected void onPause() {
		if (nfcAdapter != null)
			nfcAdapter.disableForegroundDispatch(this);
		super.onPause();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	/**
	 * 读取IC卡的卡号
	 * 
	 * @return
	 */
	protected String readICCardNo(Intent intent) {
		String cardNo = "";
		try {
			byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
			if (id == null)
				return null;
			byte[] newByte = new byte[id.length];
			for (int i = 0; i < id.length; i++) {
				newByte[i] = id[id.length - (i + 1)];
			}
			cardNo = DataConversionUtils.toHexString(newByte);
		} catch (Exception ex) {
			Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
		}
		return cardNo;
	}
}
