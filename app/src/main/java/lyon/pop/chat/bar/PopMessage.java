package lyon.pop.chat.bar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * //20210330 顯示戲劇 分集片名及內容
 */
public class PopMessage {

    String TAG = PopMessage.class.getSimpleName();
    Context context;
    View messageView;
    Handler handler = new Handler();
    TextView introductionTxt;
    TextView nameTxt;
    ImageView triangle;
    LinearLayout message_layout;
    ImageView line;
    int yPos = 0;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            introductionTxt.removeCallbacks(this);
            if(messageView!=null){
                int textHight = introductionTxt.getLineHeight();
                int textLen = TextViewLinesUtil.getTextViewLines(introductionTxt,introductionTxt.getWidth());
                int countextLen = textLen*textHight;
                int lineTop =  introductionTxt.getLayout().getLineTop(textLen);
                int pos = introductionTxt.getScrollY();
                yPos = pos+textHight/2;
                if (countextLen > (pos+textHight*(introductionTxt.getMaxLines()-1))) {
                    if(yPos>=countextLen){
                        yPos = countextLen;
                    }
                    introductionTxt.scrollTo(0, yPos);
                    introductionTxt.postDelayed(this,1000);
                }
                else {
                    yPos=0;
                    introductionTxt.scrollTo(0, 0);
                    introductionTxt.postDelayed(this,3*1000);
                }

            }
        }
    };
    public PopMessage(final Context context){
        this.context = context;
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        messageView = layoutInflater.inflate(R.layout.pop_message_layout, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int)context.getResources().getDimension(R.dimen.horizontal_width), ViewGroup.LayoutParams.WRAP_CONTENT);
        ((Activity) context).getWindow().addContentView(messageView,layoutParams);
        messageView.setVisibility(View.GONE);
        triangle = messageView.findViewById(R.id.triangle);
        nameTxt = messageView.findViewById(R.id.nameTxt);
        introductionTxt = messageView.findViewById(R.id.introductionTxt);
        introductionTxt.setMovementMethod(ScrollingMovementMethod.getInstance());
        message_layout = messageView.findViewById(R.id.message_layout);
        line = messageView.findViewById(R.id.line);
        setBackgroundColor(context.getResources().getColor(R.color.white));
        setColor(context.getResources().getColor(R.color.text_gray_block));
    }

    public void setLocation(int[] location){
        messageView.setX(location[0]);
        messageView.setY(location[1]-messageView.getHeight());
    }

    public void setViewLocation(final View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int[] location = new int[2];
                view.getLocationInWindow(location);
                messageView.setX(location[0]);
                messageView.setY(location[1]-messageView.getHeight());
            }
        }).start();

    }

    public void show(){
        Log.d(TAG,"20210331  show()");
        AlphaAnimation appearAnimation = new AlphaAnimation(0, 1);
        appearAnimation.setDuration(1500);
        messageView.startAnimation(appearAnimation);
        messageView.setVisibility(View.VISIBLE);
        if(introductionTxt!=null){
            introductionTxt.setMovementMethod(ScrollingMovementMethod.getInstance());
            yPos = 0;
            introductionTxt.scrollTo(0, yPos);
            int len = TextViewLinesUtil.getTextViewLines(introductionTxt,introductionTxt.getWidth());
            if(len>introductionTxt.getMaxLines()-1) {
                introductionTxt.postDelayed(runnable,3*1000);
            }
        }
    }

    public void dismiss(){
        Log.d(TAG,"20210331  dismiss()");
        messageView.setVisibility(View.GONE);
        introductionTxt.removeCallbacks(runnable);
    }

    public void setName(String name){
        if(nameTxt!=null){

            nameTxt.setText(name);
        }
    }

    public void setIntroduction(String introduction){
        if(introductionTxt!=null){
            introductionTxt.setText(introduction);
            introductionTxt.setMovementMethod(ScrollingMovementMethod.getInstance());

        }
    }
    private int backgroundColor;
    public PopMessage setBackgroundColor(int color)
    {
        backgroundColor = color;
        if(messageView!=null){
            if (triangle != null)
            {
                LayerDrawable drawableTriangle = (LayerDrawable) triangle.getBackground();
                GradientDrawable shapeTriangle = (GradientDrawable) (((RotateDrawable) drawableTriangle.findDrawableByLayerId(R.id.shape_id)).getDrawable());
                shapeTriangle.setColor(color);
            }

            if (message_layout != null)
            {
                Drawable tempDrawable = context.getResources().getDrawable(R.drawable.round_corner_bg);
                GradientDrawable gradientDrawable = (GradientDrawable) tempDrawable;
                gradientDrawable.setColor(color);
                message_layout.setBackground(gradientDrawable);
            }
        }
        return this;
    }

    public void setColor(int color){
        if(messageView!=null){
            nameTxt.setTextColor(color);
            introductionTxt.setTextColor(color);
            line.setBackgroundColor(color);
        }
    }
}
