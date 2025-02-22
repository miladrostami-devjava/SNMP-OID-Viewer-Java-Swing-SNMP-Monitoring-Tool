/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.snmp.swingguisnmpproject;

/**
 *
 * @author Parse
 */


import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;








public class SwingGuiSnmpProject {
    
    
        private static final String SNMP_COMMUNITY = "public";
    private static final String SNMP_ADDRESS = "udp:10.1.60.3/1161";
    
    
    
    
    

     public Map<String, String> getSnmpValue()  throws IOException{
        Address targetAddress = GenericAddress.parse(SNMP_ADDRESS);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(SNMP_COMMUNITY));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(5000);
        target.setVersion(SnmpConstants.version2c);

// Map to store values ​​received from SNMP
        Map<String, String> resultMap = new HashMap<>();

// Map to store OIDs
        Map<String, OID> oidMap = new HashMap<>();
        oidMap.put("pdu1", new OID(".1.3.6.1.4.1.1358.3.1.3.0"));//Amp type
        oidMap.put("pdu2", new OID(".1.3.6.1.4.1.1358.3.1.4.0"));//Amp type2
        oidMap.put("pdu3", new OID(".1.3.6.1.4.1.1358.3.1.6.0")); //Operating mode
        oidMap.put("pdu4", new OID(".1.3.6.1.4.1.1358.3.1.9.0"));//Input power
        oidMap.put("pdu5", new OID(".1.3.6.1.4.1.1358.3.1.10.0")); //Output power
        oidMap.put("pdu6", new OID(".1.3.6.1.4.1.1358.3.1.12.0"));//MSA input
        oidMap.put("pdu7", new OID(".1.3.6.1.4.1.1358.3.1.13.0"));//MSA output
        oidMap.put("pdu8", new OID(".1.3.6.1.4.1.1358.3.1.24.0"));//case temp
        oidMap.put("pdu9", new OID(".1.3.6.1.4.1.1358.3.1.26.0"));//Raman input power
        oidMap.put("pdu10", new OID(".1.3.6.1.4.1.1358.3.1.28.0"));//Total signal gain






// Map to store PDUs
        Map<String, PDU> pduMap = new HashMap<>();
        for (Map.Entry<String, OID> entry : oidMap.entrySet()) {
            PDU pdu = new PDU();
            pdu.setType(PDU.GET);
            pdu.add(new VariableBinding(entry.getValue()));
            pduMap.put(entry.getKey(), pdu);
        }

// Send requests and receive responses
        try  {
            
            Snmp snmp;
            snmp = new Snmp(new DefaultUdpTransportMapping());
            
            snmp.listen();

            for (Map.Entry<String, PDU> entry : pduMap.entrySet()) {
                PDU responsePDU = snmp.get(entry.getValue(), target).getResponse();
                if (responsePDU != null && !responsePDU.getVariableBindings().isEmpty()) {
// Extract the OID value and store it in the map
                    String value = responsePDU.get(0).getVariable().toString();
                    resultMap.put(entry.getKey(), value);
                } else {
                    resultMap.put(entry.getKey(), "No Response");
                }
            }
        } catch (IOException e) {

                throw new IOException("No response received for SNMP GET request.");
            }



// Return the Map containing the OID values
        return resultMap;
    }




    // Helper method to print the response
    private  void printResponse(String pduName, PDU responsePDU) {
        if (responsePDU != null && !responsePDU.getVariableBindings().isEmpty()) {
            System.out.println(pduName + ": Response Available: " + responsePDU.get(0).getVariable().toString());
        } else {
            System.out.println(pduName + ": Response Not Available");
        }
    }

    
    
    
    
    
    }

