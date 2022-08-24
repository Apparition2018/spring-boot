package com.ljh.controller;

import com.ljh.entity.Demo;
import com.ljh.repository.DemoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * DemoController
 *
 * @author ljh
 * created on 2022/8/24 16:58
 */
@RestController
public class DemoController {

    private final DemoRepository demoRepository;

    public DemoController(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @RequestMapping("/test")
    public String test() throws SocketException {
        StringBuilder ip = new StringBuilder();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            Enumeration<InetAddress> ias = ni.getInetAddresses();
            while (ias.hasMoreElements()) {
                InetAddress ia = ias.nextElement();
                if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
                    ip.append(ia.getHostAddress()).append(",");
                }
            }
        }
        return ip.substring(0, ip.length() - 1);
    }

    @RequestMapping(value = "/save/{name}")
    public Demo save(@PathVariable("name") String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        Demo demo = new Demo();
        demo.setName(name);
        return demoRepository.save(demo);
    }

    @RequestMapping("/remove/{id}")
    public void remove(@PathVariable("id") String id) {
        demoRepository.deleteById(Long.valueOf(id));
    }

    @RequestMapping("/list")
    public List<Demo> list() {
        return demoRepository.findAll();
    }

    @RequestMapping("/reset")
    public void reset() {
        demoRepository.truncateTable();
    }
}
