package cn.foxconn.matthew.mobilesafe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 获取焦点的TextView
 * @author:Matthew
 * @date:2018/2/1
 * @email:guocheng0816@163.com
 */

public class FocusTextView extends android.support.v7.widget.AppCompatTextView {

    public FocusTextView(Context context) {
        super(context);
    }
    //有属性
    public FocusTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    //有style走此方法
    public FocusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 是否有焦点，滚动TestView在滚动之前会调用此方法，强制返回TRUE表示有焦点
     * @return
     */
    @Override
    public boolean isFocused() {
        return true;
    }
}
