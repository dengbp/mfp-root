package com.yr.net.filter.errorgenerating;

import java.util.Random;

import com.yr.net.filterchain.IoFilterAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The type Error generating filter.
 */
public class ErrorGeneratingFilter extends IoFilterAdapter {
    private int removeByteProbability = 0;

    private int insertByteProbability = 0;

    private int changeByteProbability = 0;

    private int removePduProbability = 0;

    private int duplicatePduProbability = 0;

    private int resendPduLasterProbability = 0;

    private int maxInsertByte = 10;

    private boolean manipulateWrites = false;

    private boolean manipulateReads = false;

    private Random rng = new Random();

    final private Logger logger = LoggerFactory.getLogger(ErrorGeneratingFilter.class);

}