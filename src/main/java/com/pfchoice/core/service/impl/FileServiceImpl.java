package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.FileDao;
import com.pfchoice.core.entity.File;
import com.pfchoice.core.service.FileService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class FileServiceImpl implements FileService
{

    @Autowired
    private FileDao fileDao;

    @Override
    public File deleteById(final Integer id)
    {
        //Used for transaction test
        return fileDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public File findById(final Integer id)
    {
        return fileDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        return fileDao.getPage(pageNo, pageSize);
    }

    @Override
    public File save(final File bean)
    {
        //Used for transaction test
        return fileDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public File update(final File bean)
    {
        Updater<File> updater = new Updater<>(bean);
        return fileDao.updateByUpdater(updater);
    }

}
