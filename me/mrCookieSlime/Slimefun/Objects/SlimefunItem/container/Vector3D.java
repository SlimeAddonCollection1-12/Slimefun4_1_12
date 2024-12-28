/*    */ package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.container;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Vector3D
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   
/*    */   public Vector3D(double x, double y, double z) {
/* 12 */     this.x = x;
/* 13 */     this.y = y;
/* 14 */     this.z = z;
/*    */   }
/*    */   
/*    */   public double getX() {
/* 18 */     return this.x;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 22 */     return this.y;
/*    */   }
/*    */   
/*    */   public double getZ() {
/* 26 */     return this.z;
/*    */   }
/*    */   
/*    */   public static double getDistance(Vector3D point1, Vector3D point2) {
/* 30 */     return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2.0D) + Math.pow(point1.getY() - point2.getY(), 2.0D) + Math.pow(point1.getZ() - point2.getZ(), 2.0D));
/*    */   }
/*    */   
/*    */   public static Vector3D getInnerCircleCenter(Vector3D point1, Vector3D point2, Vector3D point3) {
/* 34 */     double a = getDistance(point2, point3);
/* 35 */     double b = getDistance(point1, point3);
/* 36 */     double c = getDistance(point1, point2);
/* 37 */     double x = (a * point1.getX() + b * point2.getX() + b * point3.getX()) / (a + b + c);
/* 38 */     double y = (a * point1.getY() + b * point2.getY() + b * point3.getY()) / (a + b + c);
/* 39 */     double z = (a * point1.getZ() + b * point2.getZ() + b * point3.getZ()) / (a + b + c);
/* 40 */     return new Vector3D(x, y, z);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\container\Vector3D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */