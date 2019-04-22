package dao;

import bean.FileUploadBean;

import java.util.List;

public class FileUploadDaoImp extends DAO<FileUploadBean> implements FileUploadDao{

    public List<FileUploadBean> getFiles() {
        String sql = "select id,fileName,filePath,fileDesc from upload";
        return getForList(sql);
    }


    public void save(FileUploadBean bean) {
        String sql = "insert into upload (fileName,filePath,fileDesc) values(?,?,?)";
        update(sql,bean.getFileName(),bean.getFilePath(),bean.getFileDesc());
    }
}
