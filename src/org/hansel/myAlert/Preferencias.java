package org.hansel.myAlert;
/*This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
Created by Javier Mejia @zenyagami
zenyagami@gmail.com
	*/
import org.hansel.myAlert.Utils.Util;
import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.preference.Preference;
import org.holoeverywhere.preference.Preference.OnPreferenceClickListener;
import org.holoeverywhere.preference.PreferenceScreen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.view.MenuItem;

public class Preferencias extends org.holoeverywhere.preference.PreferenceActivity{

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		addPreferencesFromResource(R.xml.preferences);
		final PreferenceScreen contactsPS =
                (PreferenceScreen) findPreference("pref_contacts_key");
        contactsPS.setIntent(
                new Intent(this, ConfigContactsActivity.class));
        
        final PreferenceScreen ongPref =
                (PreferenceScreen) findPreference("pref_key_select_ong");
        ongPref.setIntent(
                new Intent(this, PreferenceOng.class));
        
        final Preference prefAbout =
                (Preference) findPreference("pref_about");
        prefAbout.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				showDialogPre("Acerca",R.raw.cred);				
				return true;
			}
		});
        final Preference prefLegal =
                (Preference) findPreference("pref_legal");
        prefLegal.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				showDialogPre("Licencia",R.raw.gpl);
				return true;
			}
		});
        
        
	}
	private void showDialogPre(String title,int res)
	{
		LayoutInflater li = (LayoutInflater)Preferencias.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
		View v =li.inflate(R.layout.pref_dialog,null,false);
		TextView tv = (TextView) v.findViewById(R.id.txtDialogPref);
		tv.setText(Util.getStringFromRaw(getApplicationContext(), res));
		AlertDialog.Builder alert= new AlertDialog.Builder(Preferencias.this)
		.setTitle(title)
		.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}) ;
		if (v.getParent() == null) {
			alert.setView(v);
		} else {
		    v = null; //set it to null
		    // now initialized yourView and its component again
		    alert.setView(v);
		}
		alert.create().show();
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
	 switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            break;
        }
        return super.onOptionsItemSelected(item);
	}

}
