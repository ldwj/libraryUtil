package com.ldwj.library.util.treelist;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.ldwj.library.util.R;
import com.ldwj.library.util.treelist.tree.Node;
import com.ldwj.library.util.treelist.tree.TreeListViewAdapter;

import java.util.List;


public class MyTreeListViewAdapter<T> extends TreeListViewAdapter<T>
{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTreeListViewAdapter(ListView mTree, Context context, List<T> datas,
                                 int defaultExpandLevel, boolean isHide)
        throws IllegalArgumentException, IllegalAccessException
    {
        super(mTree, context, datas, defaultExpandLevel, isHide);
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.util_tree_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView)convertView.findViewById(R.id.id_treenode_icon);
            viewHolder.label = (TextView)convertView.findViewById(R.id.id_treenode_name);
            viewHolder.imgCheck = (ImageView)convertView.findViewById(R.id.id_treeNode_check);
            convertView.setTag(viewHolder);
         }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if (node.getIcon() == -1)
        {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        }
        else
        {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }

        if (node.isHideChecked())
        {
            viewHolder.imgCheck.setVisibility(View.GONE);
        }
        else
        {
            viewHolder.imgCheck.setVisibility(View.VISIBLE);
            setCheckBoxBg(viewHolder.imgCheck, node.isChecked());
        }
        viewHolder.label.setText(node.getName());

        return convertView;
    }

    private final class ViewHolder
    {
        ImageView icon;

        TextView label;

        ImageView imgCheck;
    }

    /**
     * checkbox是否显示
     * 
     * @param imgCheck
     * @param isChecked
     */
    private void setCheckBoxBg(ImageView imgCheck, boolean isChecked)
    {
        if (isChecked)
        {
            imgCheck.setImageResource(R.drawable.tree_check_box_bg_check);
        }
        else
        {
            imgCheck.setImageResource(R.drawable.tree_check_box_bg);
        }
    }
}
