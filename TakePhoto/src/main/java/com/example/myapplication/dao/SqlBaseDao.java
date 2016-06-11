package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.app.App;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class SqlBaseDao<T> {
    private SQLiteDatabase db;
    private Context mContext;
    private boolean tableisexist;
    private static volatile SqlBaseDao sqlbasedao;

    private SqlBaseDao() {
        createDatabase();
    }

    public static SqlBaseDao BaseDaoFactory() {
        // TODO Auto-generated method stub
        synchronized (SqlBaseDao.class) {
            sqlbasedao = new SqlBaseDao();
        }
        return sqlbasedao;
    }

    private void createDatabase() {
        mContext = App.getInstance();
        DBHelper dbHelper = DBHelper.getInstance(mContext, Configuration.DB_NAME,
                null, Configuration.DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }


    public void dropTable() {

    }

    /**
     * 直接隐匿性sql语句 增加、删除、修改表时、查询此方法
     *
     * @param sql
     */
    public void execSQL(String sql) {
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表中的记录
     *
     * @param table       表名
     * @param whereClause 删除条件 如:(id>? and time >?)
     * @param whereArgs   条件里的参数 用来替代"?" 第一个参数，代码第一个问号，第二个参数，代表第二个问号依次类推
     * @return 返回删除的条数
     */
    public int delete(String table, String whereClause, String[] whereArgs) {
        return db.delete(table, whereClause, whereArgs);
    }

    /**
     * 插入数据
     *
     * @param table          表名
     * @param nullColumnHack 某一个列是否可以为空
     * @param values         ContentValues值对象
     * @return 返回当前ID值，如果失败返回-1
     * @throws SQLException
     */
    public long insert(String table, String nullColumnHack, ContentValues values)
            throws SQLException {
        long num = 0;
        try {
            num = db.insertOrThrow(table, nullColumnHack, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 修改数据
     *
     * @param table       表名
     * @param values      ContentValues 表示要修改的列，如：name="mark" 即 values.put("mark");
     * @param whereClause 修改的条件 如：(_id=?)
     * @param whereArgs   条件里的参数 用来替换 "?" 第一个参数，代表第一个问号；第二个参数代表第二个问号；依次类推
     * @return 返回修改的条数
     */
    public int update(String table, ContentValues values, String whereClause,
                      String[] whereArgs) {
        return db.update(table, values, whereClause, whereArgs);
    }

    /**
     * 根据参数查询数据 无排序方式，无分组方式
     *
     * @param table         表名
     * @param columns       要查询的列名
     * @param selection     查询条件 如:(id=?)
     * @param selectionArgs 条件里的参数，用来替换？
     * @return 返回Cursor
     */
    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs) {
        return db.query(table, columns, selection, selectionArgs, null, null,
                null);

    }

    /**
     * 根据参数插叙那数据 可传入排序方式 无分组
     *
     * @param table         表名
     * @param columns       要查询的列名
     * @param selection     查询条件 如：(id=?)
     * @param selectionArgs 条件里的参数,用来替换 "?"
     * @param orderBy       排序 如：id desc
     * @return 返回游标Cursor
     */
    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String orderBy) {
        return db.query(table, columns, selection, selectionArgs, null, null,
                orderBy);
    }

    /**
     * 查询数据 指定条数和配需方式查询distinct 无分组有排序方式
     *
     * @param distinct      是否唯一每行是唯一 true：表示唯一 false:表示不唯一
     * @param table         表名
     * @param columns       要查询的列名
     * @param selection     查询条件 如：(id=?)
     * @param selectionArgs 条件里的参数，用来替换"?"
     * @param orderBy       排序方式 如id desc
     * @param limit         查询条数 如：10
     * @return 返回Cursor
     */
    public Cursor query(boolean distinct, String table, String[] columns,
                        String selection, String[] selectionArgs, String orderBy,
                        String limit) {
        return db.query(distinct, table, columns, selection, selectionArgs,
                null, null, orderBy, limit);
    }

    /**
     * 根据条件查询一个对象
     *
     * @param classz        类名必须保证与数据库中的表名相同，变量与字段一一对应
     * @param table         表名
     * @param columns       列名
     * @param selection     查询条件如 (id=?)
     * @param selectionArgs 条件里的参数用来替换"?"
     * @return
     */
    public Object queryField(Class<?> classz, String table, String[] columns,
                             String selection, String[] selectionArgs) {
        Object o = null;
        // 查询条记录
        Cursor c = db.query(table, columns, selection, selectionArgs, null,
                null, null, "1");
        if (c.moveToFirst()) {
            try {
                if (classz == Integer.TYPE) {
                    o = c.getInt(0);
                } else if (classz == String.class) {
                    o = c.getString(0);
                } else if (classz == Long.TYPE) {
                    o = c.getLong(0);
                } else if (classz == Float.TYPE) {
                    o = c.getFloat(0);
                } else if (classz == Double.TYPE) {
                    o = c.getDouble(0);
                } else if (classz == Short.TYPE) {
                    o = c.getShort(0);
                }
            } catch (Exception e) {

            }
        }
        c.close();
        return o;

    }

    /**
     * 查询条件 单个对象 无排序
     *
     * @param classz        要查询的类的字节码 如String.class
     * @param table         表名
     * @param columns       要查询的列名
     * @param selection     查询条件 如：(id=?)
     * @param selectionArgs 条件里的参数，用来替换“?”
     * @return
     */
    @SuppressWarnings("unchecked")
    public T queryObject(Class<?> classz, String table, String[] columns,
                         String selection, String[] selectionArgs) {
        // 查询单条记录
        Cursor c = db.query(table, columns, selection, selectionArgs, null,
                null, null, "1");
        T t = null;
        if (c.moveToFirst()) {
            try {
                t = (T) classz.newInstance();
                columnToField(t, c);
            } catch (Exception e) {

            }
        }
        c.close();
        return t;
    }

    private void columnToField(T t, Cursor c) {
        Field[] f = t.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            int columnIndex = c.getColumnIndex(f[i].getName());
            if (columnIndex == -1) {
                continue;
            }
            Class<?> classz = f[i].getType();// 变量类型如：String int float Object
            f[i].setAccessible(true);// 设置是否可以访问
            try {
                if (classz == Integer.TYPE) {
                    f[i].set(t, c.getInt(columnIndex));
                } else if (classz == String.class) {
                    f[i].set(t, c.getString(columnIndex));
                } else if (classz == Long.TYPE) {
                    f[i].set(t, c.getLong(columnIndex));
                } else if (classz == byte[].class) {
                    f[i].set(t, c.getBlob(columnIndex));
                } else if (classz == Float.TYPE) {
                    f[i].set(t, c.getFloat(columnIndex));
                } else if (classz == Double.TYPE) {
                    f[i].set(t, c.getDouble(columnIndex));
                } else if (classz == Short.TYPE) {
                    f[i].set(t, c.getShort(columnIndex));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据参数查询某一个对象
     *
     * @param cls
     * @param sql
     * @param selectionArgs
     * @return
     */
    public T queryObject(Class<?> cls, String sql, String[] selectionArgs) {
        Cursor mCursor = db.rawQuery(sql, selectionArgs);
        T t = null;
        if (mCursor.moveToFirst()) {
            try {
                t = (T) cls.newInstance();
                columnToField(t, mCursor);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        mCursor.close();
        return t;
    }

    /**
     * 查询数据 带分页功能
     *
     * @param classz        字节码如String.class
     * @param table         表名
     * @param columns       列名
     * @param selection     要查询的条件
     * @param selectionArgs 条件里的参数
     * @param orderBy       排序方式
     * @param pageNo        页码
     * @param pageSize      每页的个数不分页可以为null
     * @return 返回list
     */
    public List<T> queryList(Class<?> classz, String table, String[] columns,
                             String selection, String[] selectionArgs, String orderBy,
                             Integer pageNo, Integer pageSize) {
        // 分页
        if (pageNo != null || pageSize != null) {
            int begin = (pageNo - 1) * pageSize;
            orderBy = orderBy + "limit" + begin + "," + pageSize;
        }
        Cursor c = db.query(table, columns, selection, selectionArgs, null,
                null, orderBy);
        List<T> list = null;
        T t = null;
        if (c != null && c.getColumnCount() > 0 && c.moveToFirst()) {
            list = new ArrayList<T>();
            do {
                try {
                    t = (T) classz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                columnToField(t, c);
                list.add(t);
            } while (c.moveToNext());
            c.close();
        }
        return list;

    }

    /**
     * 查询数据集合
     *
     * @param cls           //类泛型类
     * @param sql           //sql执行语句
     * @param selectionArgs //占位符
     * @return
     */
    public List<T> queryList(Class<?> cls, String sql, String[] selectionArgs) {
        // sql 做好分页操作
        Cursor c = db.rawQuery(sql, selectionArgs);

        List<T> list = null;

        T t = null;
        if (c != null && c.getColumnCount() > 0 && c.moveToFirst()) {
            list = new ArrayList<T>();
            do {
                try {

                    // 生成新的实例
                    t = (T) cls.newInstance();
//					Log.d("wangmengyan","======cls  t"+t);
                } catch (Exception e) {
//					Log.e("newInstance error", "生成新的实例出错：" + e.toString());
//					System.out.println("errorlrororlrorlorlor");
                }
                // 把列的值，转换为对象里的属性的值
                columnToField(t, c);
                list.add(t);
            } while (c.moveToNext());
            //
            c.close();
        }
        return list;
    }

    /**
     * 开始事务
     */
    public boolean beginTransaction() {
        try {
            db.beginTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 提交事务及结束事务
     */
    public void commit() {
        db.setTransactionSuccessful();

    }


    public void close() {
        // TODO Auto-generated method stub
        db.endTransaction();
//		db.close();
    }


    //	/**
    //	 * 回滚事务
    //	 */
    //	public void rollback() {
    //		db.
    //	}

}
