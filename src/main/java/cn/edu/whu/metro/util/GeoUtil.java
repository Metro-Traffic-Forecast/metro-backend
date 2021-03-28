package cn.edu.whu.metro.util;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * 地理信息工具类
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/28 14:20
 **/
public class GeoUtil {

    /**
     * 计算两经纬度点之间的距离（单位：米）
     * @param lng1  经度
     * @param lat1  纬度
     * @param lng2 经度
     * @param lat2 纬度
     * @return 两点距离
     */
    public static double getDistance(double lng1,double lat1,double lng2,double lat2){
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0; // 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000.0;
        return s;
    }

    public static double getDistanceUseGeodesy(double lng1,double lat1,double lng2,double lat2){
        GeodeticCurve geodeticCurve = new GeodeticCalculator().calculateGeodeticCurve(
                Ellipsoid.WGS84, new GlobalCoordinates(lng1, lat1), new GlobalCoordinates(lng2, lat2)
        );
        return geodeticCurve.getEllipsoidalDistance();
    }

}
