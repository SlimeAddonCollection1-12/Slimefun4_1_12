/*     */ package me.mrCookieSlime.Slimefun.api;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Clock;
/*     */ 
/*     */ 
/*     */ public class SlimefunBackup
/*     */ {
/*     */   public static void start() {
/*  22 */     File folder = new File("data-storage/Slimefun/block-backups");
/*  23 */     List<File> backups = Arrays.asList(folder.listFiles());
/*  24 */     if (backups.size() > 20) {
/*  25 */       Collections.sort(backups, new Comparator<File>()
/*     */           {
/*     */             public int compare(File f1, File f2)
/*     */             {
/*     */               try {
/*  30 */                 return (int)((new SimpleDateFormat("yyyy-MM-dd-HH-mm")).parse(f1.getName().replace(".zip", "")).getTime() - (new SimpleDateFormat("yyyy-MM-dd-HH-mm")).parse(f2.getName().replace(".zip", "")).getTime());
/*  31 */               } catch (ParseException e) {
/*  32 */                 return 0;
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/*  37 */       for (int i = backups.size() - 20; i > 0; i--) {
/*  38 */         ((File)backups.get(i)).delete();
/*     */       }
/*     */     } 
/*     */     
/*  42 */     File file = new File("data-storage/Slimefun/block-backups/" + Clock.format(new Date()) + ".zip");
/*  43 */     byte[] buffer = new byte[1024];
/*     */     
/*  45 */     if (file.exists()) {
/*  46 */       file.delete();
/*     */     }
/*     */     
/*     */     try {
/*  50 */       file.createNewFile();
/*     */       
/*  52 */       ZipOutputStream output = new ZipOutputStream(new FileOutputStream(file));
/*     */       
/*  54 */       for (File f1 : (new File("data-storage/Slimefun/stored-blocks/")).listFiles()) {
/*  55 */         for (File f : f1.listFiles()) {
/*  56 */           ZipEntry entry = new ZipEntry("stored-blocks/" + f1.getName() + "/" + f.getName());
/*  57 */           output.putNextEntry(entry);
/*  58 */           FileInputStream input = new FileInputStream(f);
/*     */           
/*     */           int length;
/*  61 */           while ((length = input.read(buffer)) > 0) {
/*  62 */             output.write(buffer, 0, length);
/*     */           }
/*     */           
/*  65 */           input.close();
/*  66 */           output.closeEntry();
/*     */         } 
/*     */       } 
/*     */       
/*  70 */       for (File f : (new File("data-storage/Slimefun/universal-inventories/")).listFiles()) {
/*  71 */         ZipEntry entry = new ZipEntry("universal-inventories/" + f.getName());
/*  72 */         output.putNextEntry(entry);
/*  73 */         FileInputStream input = new FileInputStream(f);
/*     */         
/*     */         int length;
/*  76 */         while ((length = input.read(buffer)) > 0) {
/*  77 */           output.write(buffer, 0, length);
/*     */         }
/*     */         
/*  80 */         input.close();
/*  81 */         output.closeEntry();
/*     */       } 
/*     */       
/*  84 */       for (File f : (new File("data-storage/Slimefun/stored-inventories/")).listFiles()) {
/*  85 */         ZipEntry entry = new ZipEntry("stored-inventories/" + f.getName());
/*  86 */         output.putNextEntry(entry);
/*  87 */         FileInputStream input = new FileInputStream(f);
/*     */         
/*     */         int length;
/*  90 */         while ((length = input.read(buffer)) > 0) {
/*  91 */           output.write(buffer, 0, length);
/*     */         }
/*     */         
/*  94 */         input.close();
/*  95 */         output.closeEntry();
/*     */       } 
/*     */       
/*  98 */       if ((new File("data-storage/Slimefun/stored-chunks/chunks.sfc")).exists()) {
/*  99 */         ZipEntry entry = new ZipEntry("stored-chunks/chunks.sfc");
/* 100 */         output.putNextEntry(entry);
/* 101 */         FileInputStream input = new FileInputStream(new File("data-storage/Slimefun/stored-chunks/chunks.sfc"));
/*     */         
/*     */         int length;
/* 104 */         while ((length = input.read(buffer)) > 0) {
/* 105 */           output.write(buffer, 0, length);
/*     */         }
/*     */         
/* 108 */         input.close();
/* 109 */         output.closeEntry();
/*     */       } 
/*     */       
/* 112 */       output.close();
/* 113 */       System.out.println("[Slimefun] 备份方块信息至 " + file.getName());
/* 114 */     } catch (IOException e) {
/* 115 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\api\SlimefunBackup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */