package com.pfcti.springdata.service;

import com.pfcti.springdata.repository.InversionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class InversionService {
    private InversionRepository inversionRepository;
}
