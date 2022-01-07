package cn.lessann.studentmanager.function;

import cn.lessann.studentmanager.exception.FileDbPathNotFindException;
import cn.lessann.studentmanager.util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 文件数据库相关操作
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/6 8:32 下午
 */
public abstract class FileDb<T> {

    private T t;

    public FileDb(T t) {
        this.t = t;
    }

    public List<T> getAll() {
        try {
            Class clazz = t.getClass();
            File dbFile = getDbFile();
            T t = (T) clazz.newInstance();

            if (dbFile.length() <= 0) {
                return null;
            }

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dbFile));
            List<T> list = (List<T>) inputStream.readObject();
            inputStream.close();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T add(T bean) {
        try {
            List<T> list = getAll();

            if (list == null || list.size() <= 0) {
                list = new ArrayList<>();
            } else {
                if (isExists(list, bean)) {
                    System.out.println("内容已存在");
                    return null;
                }
            }

            File dbFile = getDbFile();
            // 新增对象到列表中
            list.add(bean);
            // 写入数据库中
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dbFile));
            outputStream.writeObject(list);
            outputStream.close();

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T delete(T bean) {
        if (bean == null) {
            System.out.println("待删除对象为空，删除结束！");
            return bean;
        }

        String primaryKey = getPrimaryKey(bean);
        if (primaryKey == null || primaryKey.length() <= 0) {
            System.out.println("删除对象不存在主键，无法删除");
            return bean;
        }

        List<T> list = getAll();
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            String nextPrimaryKey = getPrimaryKey(next);
            if (nextPrimaryKey.equals(primaryKey)) {
                iterator.remove();
            }
        }

        // 写内容
        writerFileDb(list);

        return bean;
    }

    public T update(T bean, T newBean) {
        if (bean == null) {
            System.out.println("待修改对象为空，修改结束！");
            return bean;
        }

        String primaryKey = getPrimaryKey(bean);

        List<T> list = getAll();

        int flag = -1;
        for (int i = 0; i < list.size(); i++) {
            String itemPrimaryKey = getPrimaryKey(list.get(i));
            if (itemPrimaryKey.equals(primaryKey)) {
                flag = i;
                break;
            }
        }

        if (flag != -1) {
            list.remove(flag);
            list.add(flag, newBean);
        }

        writerFileDb(list);

        return newBean;
    }

    private boolean isExists(List<T> list, T bean) {
        try {
            // 获得被操作对象类对象
            Class<?> clazz = bean.getClass();

            // 获得name属性值，开启了访问权限
            String name = (String) Util.getFieldValue(bean.getClass(), bean, "name");

            for (T item : list) {
                // 此处我们设置name为类主键，判断文件中是否已存在就使用此字段
                String tempName = (String) Util.getFieldValue(item.getClass(), item, "name");
                if (name.equals(tempName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public File getDbFile() throws Exception {
        String path = FileDb.class.getResource("/db").getPath();
        if (Objects.isNull(path)) {
            throw new FileDbPathNotFindException("文件路径未找到");
        }
        File file = new File(path + "/" + t.getClass().getName() + ".jdb");
        // 文件是否存在
        if (!file.exists()) {
            // 不存在创建文件
            file.createNewFile();
        }

        return file;
    }

    /**
     * 获得所有对象的主键，本系统默认主键为name
     *
     * @param bean 被操作对象
     * @return 获得到的主键内容
     */
    public String getPrimaryKey(T bean) {
        try {
            if (bean == null) {
                return null;
            }
            return (String) Util.getFieldValue(bean.getClass(), bean, "name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存内容到DB文件中，为空时直接结束存储
     *
     * @param list 待存储内容
     */
    public void writerFileDb(List<T> list) {
        try {
            if (list == null || list.size() <= 0) {
                System.out.println("写入内容为空，写入结束");
                return;
            }
            // 获得对应文件
            File file = getDbFile();
            // 获得文件流存内容
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(list);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
