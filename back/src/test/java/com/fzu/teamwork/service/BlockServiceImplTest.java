package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.BlockDao;
import com.fzu.teamwork.model.Block;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class BlockServiceImplTest {

    @Autowired
    private BlockService blockService;
    @Resource
    private BlockDao blockDao;

    //测试新增临时板块
    @Test
    void addBlock() {
        Block newBlock = new Block();
        Block oldBlock = blockService.getBlock();

        newBlock.setBlockName("testBlock");
        newBlock.setKeyWord("test");

        if(oldBlock != null){//已经有临时板块
            //判断添加临时模块是否返回false
            Assert.assertFalse(blockService.insertBlock(newBlock));
            blockService.deleteBlock();//先将临时版块移除
        }
        //添加新的临时板块，判断结果
        Assert.assertTrue(blockService.insertBlock(newBlock));
        //重复添加判断结果
        Assert.assertFalse(blockService.insertBlock(newBlock));
        //判断是否成功添加到数据库
        Assert.assertNotNull(blockService.getBlock());
        //判断是否是刚添加的测试模块
        Assert.assertEquals(newBlock.getBlockName(), blockService.getBlock().getBlockName());
        Assert.assertEquals(newBlock.getKeyWord(), blockService.getBlock().getKeyWord());

        //删除添加的测试数据
        blockService.deleteBlock();
        if(oldBlock != null){//恢复原来的数据
            blockService.insertBlock(oldBlock);
        }
    }

    //测试删除模块函数
    @Test
    void deleteBlock() {
        Block oldBlock = blockService.getBlock();//获取数据库的临时板块
        Block newBlock = new Block();
        newBlock.setBlockName("testBlock");
        newBlock.setKeyWord("test");

        if(oldBlock == null){//当前数据库没有临时板块
            //判断删除结果是否为false
            Assert.assertFalse(blockService.deleteBlock());
            blockService.insertBlock(newBlock);
        }
        //判断撤销临时板块结果
        Assert.assertTrue(blockService.deleteBlock());
        //重新获取判断是否成功删除
        Assert.assertNull(blockService.getBlock());
        //恢复测试前数据
        if(oldBlock != null){
            blockService.insertBlock(oldBlock);
        }
    }

    //测试获取临时板块函数
    @Test
    void getBlock() {
        Block block1, block2;
        Block oldBlock = null;
        Block newBlock = new Block();
        newBlock.setBlockName("testBlock");
        newBlock.setKeyWord("test");
        if(blockDao.selectByExample(null).size() == 0){//数据库中不存在临时板块
            Assert.assertNull(blockService.getBlock());
            //添加测试临时板块
            blockService.insertBlock(newBlock);//添加临时板块
        }else{
            oldBlock = blockDao.selectByExample(null).get(0);
        }

        block1 = blockDao.selectByExample(null).get(0);//直接通过dao类获取数据库数据
        block2 = blockService.getBlock();
        Assert.assertEquals(block1.getBlockName(), block2.getBlockName());
        Assert.assertEquals(block1.getKeyWord(), block2.getKeyWord());

        //删除新添加的测试数据
        blockService.deleteBlock();
        //恢复测试前数据库数据
        if(oldBlock != null){
            blockService.insertBlock(oldBlock);
        }

    }
}