Goto View
-----
The goto view is an open source android library that create different section tags from a recyclerview. The section tags allow for fast browsing through the recyclerview.

Requirements
-----
The library requires Android **API Level 9+**.

Dependency
-----
    repositories {
        maven {
            url  "http://ember24.bintray.com/maven"
        }
    }
    
    dependencies {
        compile 'com.ember24.gotoview:goto-view:0.1.2'
    }

Screenshots
-----
![](https://github.com/Ember24/GotoView/blob/master/screenshots/screenshot_1.png?raw=true) ![](https://github.com/Ember24/GotoView/blob/master/screenshots/screenshot_2.png?raw=true)

Usage
-----
**Step 1:** add this to your layout XML:

    <com.ember24.gotoview.GoToView
        android:id="@+id/gotoView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

**Step 2:** set recycler view:

    GoToView goto = (GoToView) findViewById(R.id.gotoView);
    goto.setRecyclerView(dependantRecycleView);
    
    //if adapter has not been set, you will need to call the following method
    goto.setGotoSectionAdapter();

**Step 3:** make recyclerview's adapter implement GotoSection:

    public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.MovieCharacterViewHolder>
    implements GotoSection {
        @Override
        public List<SectionItem> getSections()
        {
            List<SectionItem> sections = new ArrayList<>();
            String sectionName = "";
            for (int i = 0; i < dataArray.size(); i++) {
                if(!sectionName.equalsIgnoreCase(dataArray.get(i).movie)) {
                    sectionName = dataArray.get(i).movie;
                    SectionItem item = new SectionItem(sectionName, i);
                    sections.add(item);
                }
            }

            return sections;
        }

        @Override
        public String getCurrentSection(int pos) {
            if (pos < 0 || pos >= dataArray.size())
                return null;
            return dataArray.get(pos).movie;
        }
    }
    
**Important:** refresh adapter after changing the recyclerview's dataset:

    goto.refresh();

Attributes
------
The following xml tag contains all attributes that can currently be set

    <com.ember24.gotoview.GoToView
        android:id="@+id/gotoView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        goto:goto_radius="20"
        goto:goto_stroke="5"
        goto:goto_color="#d10404"
        goto:goto_textColor="#d10404"
        goto:goto_selectedColor="#0095ff"
        goto:goto_textSelectedColor="#ffffff" />

Credits
------
- Used [mustafasevgi/RecyclerViewPositionHelper.java](https://gist.github.com/mustafasevgi/7dcb18166aaf9944e6450ec2878a646a)
- Based on [viethoa/recyclerview-alphabet-fast-scroller-android](https://github.com/viethoa/recyclerview-alphabet-fast-scroller-android)
    
License
-------

    Copyright 2017 Ikraam Moothian Pillay

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



