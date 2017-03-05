Goto View
-----
The goto view is an open source android library that create different section tags from a recyclerview. The section tags allow for fast browsing through the recyclerview.

Screenshots
-----
![](https://github.com/Ember24/GotoView/blob/master/screenshots/screenshot_1.png?raw=true) ![](https://github.com/Ember24/GotoView/blob/master/screenshots/screenshot_2.png?raw=true)

How to use
-----
**Step 1:** add this to your layout XML:

    <com.ember24.gotoview.GoToView
        android:id="@+id/gotoView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

**Step 2:** set recycler view:

    GoToView goto = (GoToView) findViewById(R.id.gotoView);
    goto.setRecyclerView(dependantRecycleView);

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



