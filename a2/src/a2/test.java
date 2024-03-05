public class test {

    int x = CAMERA_WIDTH_PIXEL/2 - buoy_x_pixel();
    int y = CAMERA_HEIGHT_PIXELS/2 - buoy_y_pixel();
    
    while(x != 0 && y != 0) {
        set_y_velocity(x/(math.abs(x)));
        set_z_velocity(y/(math.abs(y)));
        x = CAMERA_WIDTH_PIXEL/2 - buoy_x_pixel();
        y = CAMERA_HEIGHT_PIXELS/2 - buoy_y_pixel();
    }
    set_x_velocity(1);
}


    
