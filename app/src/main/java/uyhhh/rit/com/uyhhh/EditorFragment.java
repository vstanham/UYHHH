/***
  Copyright (c) 2012-14 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Android Development_
    http://commonsware.com/Android
 */

package uyhhh.rit.com.uyhhh;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class EditorFragment extends Fragment {
  private static final String KEY_POSITION="position";

  static EditorFragment newInstance(int position) {
    EditorFragment frag=new EditorFragment();
    Bundle args=new Bundle();

    args.putInt(KEY_POSITION, position);
    frag.setArguments(args);

    return(frag);
  }
  //que hace getTitle????
  static String getTitle(Context ctxt, int position) {
    return(String.format(ctxt.getString(R.string.hint), position + 1));
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {

    int position=getArguments().getInt(KEY_POSITION, -1);
    View result=null;
    //TODO agregar todos los fragmentos con un switch
    switch (position){
        case 0:
            result = inflater.inflate(R.layout.nexthash, container, false);
            //TODO leer datos de la bd local del movil y cargarla en variables
            //TODO mostrar los datos
            TextView textLocation = (TextView) result.findViewById(R.id.tvLocation);
            textLocation.setTypeface(null, Typeface.BOLD);
            TextView textDate = (TextView) result.findViewById(R.id.tvDate);
            textDate.setTypeface(null, Typeface.BOLD);
            TextView textTime = (TextView) result.findViewById(R.id.tvTime);
            textTime.setTypeface(null, Typeface.BOLD);
            TextView textHare = (TextView) result.findViewById(R.id.tvHares);
            textHare.setTypeface(null, Typeface.BOLD);

            //TODO Si faltan dos dias o menos mostrar el days to go
            break;
        case 1:
            result = inflater.inflate(R.layout.weather, container, false);
            TextView tvweather= (TextView) result.findViewById(R.id.tvweather);
            break;
        case 2:
            result = inflater.inflate(R.layout.songs,container,false);
            TextView tvsongs = (TextView) result.findViewById(R.id.tvsongs);
            break;
        case 3:
            result = inflater.inflate(R.layout.contact,container,false);
            TextView tvfollowus=(TextView) result.findViewById(R.id.tvfollowusContact);
            break;
        case 4:
            result = inflater.inflate(R.layout.calendar,container,false);
            try
            {
                CalendarView cv = (CalendarView) result.findViewById(R.id.calendarView);
                Class<?> cvClass = cv.getClass();
                Field field = cvClass.getDeclaredField("mMonthName");
                field.setAccessible(true);

                try
                {
                    TextView tv = (TextView) field.get(cv);
                    tv.setTextColor(Color.WHITE);
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
            catch (NoSuchFieldException e)
            {
                e.printStackTrace();
            }


            break;
        default:
            result = inflater.inflate(R.layout.nexthash, container, false);
            break;
    }
    /*if(position==0) {
        result = inflater.inflate(R.layout.nexthash, container, false);
        //EditText editor = (EditText) result.findViewById(R.id.editor);
        //editor.setHint(getTitle(getActivity(), position));
    }else{
        result = inflater.inflate(R.layout.editor2, container, false);
        //EditText editor = (EditText) result.findViewById(R.id.editor2);
    }*/



    return(result);
  }
}