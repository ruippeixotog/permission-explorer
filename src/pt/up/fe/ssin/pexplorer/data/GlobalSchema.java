/*
 * Copyright (C) 2012 Rui Gonçalves and Daniel Cibrão
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pt.up.fe.ssin.pexplorer.data;

import java.io.IOException;
import java.util.Scanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GlobalSchema {

    public static final String PERMISSION_TABLE_NAME = "permissions";
    private static final String CREATE_SCRIPT_FILE = "data.sql";

	public static void createSchema(SQLiteDatabase db) {	
		db.execSQL("CREATE TABLE " + PERMISSION_TABLE_NAME + " ( " + PermissionExplorerContentProvider.PERMISSION_ID +
        		" INTEGER PRIMARY KEY AUTOINCREMENT, " + PermissionExplorerContentProvider.PERMISSION_NAME +
        		" VARCHAR(255), " + PermissionExplorerContentProvider.PERMISSION_COMMON + " INTEGER ," +
        		PermissionExplorerContentProvider.PERMISSION_DESCRIPTION + " VARCHAR(255));");
	}
	
	public static void readDataFromDBFile(SQLiteDatabase db, Context context,String dataBaseName){
		Scanner sc = null;
        try {
                sc = new Scanner(context.getAssets().open(CREATE_SCRIPT_FILE));
                sc.useDelimiter(";");
                while (sc.hasNext())
                {
                        String sqlCmd = sc.next();
                        Log.i("SQL Command", sqlCmd);
                        db.execSQL(sqlCmd);
                }
        } catch (IOException e) {
                Log.e("DB " + dataBaseName, "Failed to read script file.");
                e.printStackTrace();
                return;
        } finally {
                if (sc != null)
                    sc.close();
        }
	}
}
