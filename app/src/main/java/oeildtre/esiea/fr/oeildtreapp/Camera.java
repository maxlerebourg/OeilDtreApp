package oeildtre.esiea.fr.oeildtreapp;


        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.webkit.WebView;
        import android.widget.ImageButton;
        import android.widget.LinearLayout;

public class Camera extends Fragment {

    WebView webView;
    private ImageButton left,up,down,right,camera,center;
    private LinearLayout panel;
    private boolean visible = false;
    private GraphService gs = new GraphService();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View cam = inflater.inflate(R.layout.camera, container, false);
        webView = (WebView) cam.findViewById(R.id.webView);

        //webView.loadUrl(gs.getSource()+":8090/?action=stream");
        webView.loadUrl("http://www.journaldugamer.com/files/2017/02/Zelda-Breath-of-the-wild-DLC.jpg");
        left = (ImageButton) cam.findViewById(R.id.left);
        up = (ImageButton) cam.findViewById(R.id.up);
        down = (ImageButton) cam.findViewById(R.id.down);
        right = (ImageButton) cam.findViewById(R.id.right);
        camera = (ImageButton) cam.findViewById(R.id.cam);
        center = (ImageButton) cam.findViewById(R.id.center);
        panel = (LinearLayout) cam.findViewById(R.id.buttonPanel);
        left.setRotation(180);
        up.setRotation(-90);
        down.setRotation(90);
        /*left.setAlpha(0);
        up.setAlpha(0);
        down.setAlpha(0);
        right.setAlpha(0);
        camera.setAlpha(0);
        center.setAlpha(0);*/
        webView.bringToFront();
        webView.setOnTouchListener(new View.OnTouchListener() {

            final static int FINGER_RELEASED = 0;
            final static int FINGER_TOUCHED = 1;
            final static int FINGER_DRAGGING = 2;
            final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;


            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (fingerState == FINGER_RELEASED) fingerState = FINGER_TOUCHED;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    case MotionEvent.ACTION_UP:
                        if(fingerState != FINGER_DRAGGING) {
                            fingerState = FINGER_RELEASED;
                            Log.e("WebViewListener","click");
                            if (visible) {
                                panel.bringToFront();
                                /*left.setAlpha(100);
                                up.setAlpha(100);
                                down.setAlpha(100);
                                right.setAlpha(100);
                                camera.setAlpha(100);
                                center.setAlpha(100);*/

                                visible = false;
                            } else {
                                webView.bringToFront();
                                /*left.setAlpha(0);
                                up.setAlpha(0);
                                down.setAlpha(0);
                                right.setAlpha(0);
                                camera.setAlpha(0);
                                center.setAlpha(0);*/
                                visible = true;
                            }
                        }
                        else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING) fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    default:
                        fingerState = FINGER_UNDEFINED;
                }

                return false;
            }
        });
        return cam;
    }
}