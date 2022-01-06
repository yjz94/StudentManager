package cn.lessann.studentmanager.exception;

/**
 * 文件路径未找到
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/6 8:44 下午
 */
public class FileDbPathNotFindException extends Exception {
    private static final long serialVersionUID = -6541809848875446182L;

    public FileDbPathNotFindException(String message) {
        super(message);
    }
}
