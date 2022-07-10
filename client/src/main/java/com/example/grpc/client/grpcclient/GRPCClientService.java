package com.example.grpc.client.grpcclient;

import com.example.grpc.server.grpcserver.MultRequest;
import com.example.grpc.server.grpcserver.MultReply;
import com.example.grpc.server.grpcserver.MatrixServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class GRPCClientService {
 public String mult(int A[][],int B[][],int MAX){
  System.out.println("\nA: \n" + Arrays.deepToString(A));
  System.out.println("\nB: \n" + Arrays.deepToString(B));
  int bSize=MAX/2;
  int[][] A1 = new int[MAX][MAX];
  int[][] A2 = new int[MAX][MAX];
  int[][] A3 = new int[MAX][MAX];
  int[][] B1 = new int[MAX][MAX];
  int[][] B2 = new int[MAX][MAX];
  int[][] B3 = new int[MAX][MAX];
  int[][] C1 = new int[MAX][MAX];
  int[][] C2 = new int[MAX][MAX];
  int[][] C3 = new int[MAX][MAX];
  int[][] D1 = new int[MAX][MAX];
  int[][] D2 = new int[MAX][MAX];
  int[][] D3 = new int[MAX][MAX];
  int[][] res= new int[MAX][MAX];
  for (int i = 0; i < bSize; i++) 
    { 
        for (int j = 0; j < bSize; j++)
        {
            A1[i][j]=A[i][j];
            A2[i][j]=B[i][j];
        }
    }
  for (int i = 0; i < bSize; i++) 
    { 
        for (int j = bSize; j < MAX; j++)
        {
            B1[i][j-bSize]=A[i][j];
            B2[i][j-bSize]=B[i][j];
        }
    }
  for (int i = bSize; i < MAX; i++) 
    { 
        for (int j = 0; j < bSize; j++)
        {
            C1[i-bSize][j]=A[i][j];
            C2[i-bSize][j]=B[i][j];
        }
    } 
  for (int i = bSize; i < MAX; i++) 
    { 
        for (int j = bSize; j < MAX; j++)
        {
            D1[i-bSize][j-bSize]=A[i][j];
            D2[i-bSize][j-bSize]=B[i][j];
        }
    }  
    
  ManagedChannel channel1 = ManagedChannelBuilder.forAddress("18.205.149.2",9090).usePlaintext().build();
  MatrixServiceGrpc.MatrixServiceBlockingStub stub1 = MatrixServiceGrpc.newBlockingStub(channel1);
  ManagedChannel channel2 = ManagedChannelBuilder.forAddress("54.162.87.107",9090).usePlaintext().build();
  MatrixServiceGrpc.MatrixServiceBlockingStub stub2 = MatrixServiceGrpc.newBlockingStub(channel2);
  ManagedChannel channel3 = ManagedChannelBuilder.forAddress("100.25.181.207",9090).usePlaintext().build();
  MatrixServiceGrpc.MatrixServiceBlockingStub stub3 = MatrixServiceGrpc.newBlockingStub(channel3);
  ManagedChannel channel4 = ManagedChannelBuilder.forAddress("3.95.150.137",9090).usePlaintext().build();
  MatrixServiceGrpc.MatrixServiceBlockingStub stub4 = MatrixServiceGrpc.newBlockingStub(channel4);
  ManagedChannel channel5 = ManagedChannelBuilder.forAddress("3.82.114.40",9090).usePlaintext().build();
  MatrixServiceGrpc.MatrixServiceBlockingStub stub5 = MatrixServiceGrpc.newBlockingStub(channel5);
  ManagedChannel channel6 = ManagedChannelBuilder.forAddress("52.90.32.4",9090).usePlaintext().build();
  MatrixServiceGrpc.MatrixServiceBlockingStub stub6 = MatrixServiceGrpc.newBlockingStub(channel6);
  ManagedChannel channel7 = ManagedChannelBuilder.forAddress("3.95.225.61",9090).usePlaintext().build();
  MatrixServiceGrpc.MatrixServiceBlockingStub stub7 = MatrixServiceGrpc.newBlockingStub(channel7);
  ManagedChannel channel8 = ManagedChannelBuilder.forAddress("18.234.168.17",9090).usePlaintext().build();
  MatrixServiceGrpc.MatrixServiceBlockingStub stub8 = MatrixServiceGrpc.newBlockingStub(channel8);//ssh -i 1.pem ubuntu@18.205.149.2 http://18.205.149.2:8082/

  long starttime = System.nanoTime();
  MultReply atest = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(A2)).build());
  long endtime = System.nanoTime();
  long footprint = endtime-starttime;
  long deadline = footprint;
  long lowesttime = 9224616;//2 x 2 matrix
  long highesttime = 20487476;//100 x 100 matrix

  if(deadline < lowesttime){    
  MultReply a31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(A2)).build());
  MultReply a32 = stub2.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(C2)).build());
  MultReply b31 = stub3.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(B2)).build());
  MultReply b32 = stub4.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(D2)).build());
  MultReply c31 = stub5.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(A2)).build());
  MultReply c32 = stub6.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(C2)).build());
  MultReply d31 = stub7.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(B2)).build());
  MultReply d32 = stub8.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(D2)).build()); 
  MultReply aa = stub1.addblock(MultRequest.newBuilder().addAllA(a31.getCList()).addAllB(a32.getCList()).build());
  MultReply bb = stub1.addblock(MultRequest.newBuilder().addAllA(b31.getCList()).addAllB(b32.getCList()).build());
  MultReply cc = stub1.addblock(MultRequest.newBuilder().addAllA(c31.getCList()).addAllB(c32.getCList()).build());
  MultReply dd = stub1.addblock(MultRequest.newBuilder().addAllA(d31.getCList()).addAllB(d32.getCList()).build());
  A3=arr(aa.getCList(),MAX);
  B3=arr(bb.getCList(),MAX);
  C3=arr(cc.getCList(),MAX);
  D3=arr(dd.getCList(),MAX);
  System.out.print("8 servers required");

  }else if(deadline < (lowesttime*1.2)){
   MultReply a31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(A2)).build());
  MultReply a32 = stub2.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(C2)).build());
  MultReply b31 = stub3.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(B2)).build());
  MultReply b32 = stub4.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(D2)).build());
  MultReply c31 = stub5.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(A2)).build());
  MultReply c32 = stub6.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(C2)).build());
  MultReply d31 = stub7.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(B2)).build());
  MultReply d32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(D2)).build()); 
  MultReply aa = stub1.addblock(MultRequest.newBuilder().addAllA(a31.getCList()).addAllB(a32.getCList()).build());
  MultReply bb = stub1.addblock(MultRequest.newBuilder().addAllA(b31.getCList()).addAllB(b32.getCList()).build());
  MultReply cc = stub1.addblock(MultRequest.newBuilder().addAllA(c31.getCList()).addAllB(c32.getCList()).build());
  MultReply dd = stub1.addblock(MultRequest.newBuilder().addAllA(d31.getCList()).addAllB(d32.getCList()).build());
  A3=arr(aa.getCList(),MAX);
  B3=arr(bb.getCList(),MAX);
  C3=arr(cc.getCList(),MAX);
  D3=arr(dd.getCList(),MAX);
  System.out.print("7 servers required");

  }else if(deadline < (lowesttime*1.4)){
  MultReply a31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(A2)).build());
  MultReply a32 = stub2.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(C2)).build());
  MultReply b31 = stub3.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(B2)).build());
  MultReply b32 = stub4.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(D2)).build());
  MultReply c31 = stub5.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(A2)).build());
  MultReply c32 = stub6.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(C2)).build());
  MultReply d31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(B2)).build());
  MultReply d32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(D2)).build()); 
  MultReply aa = stub1.addblock(MultRequest.newBuilder().addAllA(a31.getCList()).addAllB(a32.getCList()).build());
  MultReply bb = stub1.addblock(MultRequest.newBuilder().addAllA(b31.getCList()).addAllB(b32.getCList()).build());
  MultReply cc = stub1.addblock(MultRequest.newBuilder().addAllA(c31.getCList()).addAllB(c32.getCList()).build());
  MultReply dd = stub1.addblock(MultRequest.newBuilder().addAllA(d31.getCList()).addAllB(d32.getCList()).build());
  A3=arr(aa.getCList(),MAX);
  B3=arr(bb.getCList(),MAX);
  C3=arr(cc.getCList(),MAX);
  D3=arr(dd.getCList(),MAX);
  System.out.print("6 servers required");

  }else if(deadline < (lowesttime*1.6)){
   MultReply a31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(A2)).build());
  MultReply a32 = stub2.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(C2)).build());
  MultReply b31 = stub3.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(B2)).build());
  MultReply b32 = stub4.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(D2)).build());
  MultReply c31 = stub5.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(A2)).build());
  MultReply c32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(C2)).build());
  MultReply d31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(B2)).build());
  MultReply d32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(D2)).build()); 
  MultReply aa = stub1.addblock(MultRequest.newBuilder().addAllA(a31.getCList()).addAllB(a32.getCList()).build());
  MultReply bb = stub1.addblock(MultRequest.newBuilder().addAllA(b31.getCList()).addAllB(b32.getCList()).build());
  MultReply cc = stub1.addblock(MultRequest.newBuilder().addAllA(c31.getCList()).addAllB(c32.getCList()).build());
  MultReply dd = stub1.addblock(MultRequest.newBuilder().addAllA(d31.getCList()).addAllB(d32.getCList()).build());
  A3=arr(aa.getCList(),MAX);
  B3=arr(bb.getCList(),MAX);
  C3=arr(cc.getCList(),MAX);
  D3=arr(dd.getCList(),MAX);
  System.out.print("5 servers required");

  }else if(deadline < (lowesttime*1.8)){
  MultReply a31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(A2)).build());
  MultReply a32 = stub2.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(C2)).build());
  MultReply b31 = stub3.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(B2)).build());
  MultReply b32 = stub4.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(D2)).build());
  MultReply c31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(A2)).build());
  MultReply c32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(C2)).build());
  MultReply d31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(B2)).build());
  MultReply d32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(D2)).build()); 
  MultReply aa = stub1.addblock(MultRequest.newBuilder().addAllA(a31.getCList()).addAllB(a32.getCList()).build());
  MultReply bb = stub1.addblock(MultRequest.newBuilder().addAllA(b31.getCList()).addAllB(b32.getCList()).build());
  MultReply cc = stub1.addblock(MultRequest.newBuilder().addAllA(c31.getCList()).addAllB(c32.getCList()).build());
  MultReply dd = stub1.addblock(MultRequest.newBuilder().addAllA(d31.getCList()).addAllB(d32.getCList()).build());
  A3=arr(aa.getCList(),MAX);
  B3=arr(bb.getCList(),MAX);
  C3=arr(cc.getCList(),MAX);
  D3=arr(dd.getCList(),MAX);
  System.out.print("4 servers required");

  }else if(deadline < (lowesttime*2)){
   MultReply a31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(A2)).build());
  MultReply a32 = stub2.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(C2)).build());
  MultReply b31 = stub3.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(B2)).build());
  MultReply b32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(D2)).build());
  MultReply c31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(A2)).build());
  MultReply c32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(C2)).build());
  MultReply d31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(B2)).build());
  MultReply d32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(D2)).build()); 
  MultReply aa = stub1.addblock(MultRequest.newBuilder().addAllA(a31.getCList()).addAllB(a32.getCList()).build());
  MultReply bb = stub1.addblock(MultRequest.newBuilder().addAllA(b31.getCList()).addAllB(b32.getCList()).build());
  MultReply cc = stub1.addblock(MultRequest.newBuilder().addAllA(c31.getCList()).addAllB(c32.getCList()).build());
  MultReply dd = stub1.addblock(MultRequest.newBuilder().addAllA(d31.getCList()).addAllB(d32.getCList()).build());
  A3=arr(aa.getCList(),MAX);
  B3=arr(bb.getCList(),MAX);
  C3=arr(cc.getCList(),MAX);
  D3=arr(dd.getCList(),MAX);
  System.out.print("2/3 servers required");

  }else if(deadline > (lowesttime*2) || deadline > highesttime){
  MultReply a31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(A2)).build());
  MultReply a32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(C2)).build());
  MultReply b31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(A1)).addAllB(flat(B2)).build());
  MultReply b32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(B1)).addAllB(flat(D2)).build());
  MultReply c31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(A2)).build());
  MultReply c32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(C2)).build());
  MultReply d31 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(C1)).addAllB(flat(B2)).build());
  MultReply d32 = stub1.multiplyblock(MultRequest.newBuilder().addAllA(flat(D1)).addAllB(flat(D2)).build()); 
  MultReply aa = stub1.addblock(MultRequest.newBuilder().addAllA(a31.getCList()).addAllB(a32.getCList()).build());
  MultReply bb = stub1.addblock(MultRequest.newBuilder().addAllA(b31.getCList()).addAllB(b32.getCList()).build());
  MultReply cc = stub1.addblock(MultRequest.newBuilder().addAllA(c31.getCList()).addAllB(c32.getCList()).build());
  MultReply dd = stub1.addblock(MultRequest.newBuilder().addAllA(d31.getCList()).addAllB(d32.getCList()).build());
  A3=arr(aa.getCList(),MAX);
  B3=arr(bb.getCList(),MAX);
  C3=arr(cc.getCList(),MAX);
  D3=arr(dd.getCList(),MAX);
  System.out.print("1 server required");
  }

  for (int i = 0; i < bSize; i++) 
    { 
        for (int j = 0; j < bSize; j++)
        {
            res[i][j]=A3[i][j];
        }
    }
  for (int i = 0; i < bSize; i++) 
    { 
        for (int j = bSize; j < MAX; j++)
        {
            res[i][j]=B3[i][j-bSize];
        }
    }
  for (int i = bSize; i < MAX; i++) 
    { 
        for (int j = 0; j < bSize; j++)
        {
            res[i][j]=C3[i-bSize][j];
        }
    } 
  for (int i = bSize; i < MAX; i++) 
    { 
        for (int j = bSize; j < MAX; j++)
        {
            res[i][j]=D3[i-bSize][j-bSize];
        }
    } 
  channel1.shutdown();channel2.shutdown();channel3.shutdown();channel4.shutdown();channel5.shutdown();channel6.shutdown();channel7.shutdown();channel8.shutdown();
  return ("Result: \n" + Arrays.deepToString(res));
}
static int[][] arr (List<Integer> z,int MAX){
  int a[][] = new int[MAX][MAX];

  int i =0;
  int x =0;
  for(int k = 0; k<MAX; k++) {
      x = k;
      for(int j = 0; j<MAX; j++){
         a[x][j] = z.get(i);
         i++;
      }
  }

  return a;
}
static List<Integer> flat(int z[][]){
 List a = new ArrayList();
 int q = 0;
 for (int i = 0; i < z.length; i++) {
  for (int j = 0; j < z[1].length; j++) {
    a.add(q,z[i][j]);
    q += 1;
  }
 }
 return a;
}
}