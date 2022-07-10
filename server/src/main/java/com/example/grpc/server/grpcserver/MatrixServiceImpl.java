package com.example.grpc.server.grpcserver;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import java.util.List;
import java.util.ArrayList;

@GrpcService
public class MatrixServiceImpl extends MatrixServiceGrpc.MatrixServiceImplBase {
@Override
public void multiplyblock(MultRequest request, StreamObserver<MultReply> reply)
{   
    int MAX = (int)Math.sqrt(request.getAList().size());
    int C[][]= new int[MAX][MAX];
    int A[][] = arr(request.getAList(),MAX);
    int B[][] = arr(request.getBList(),MAX);

    for (int i=0;i<MAX/2;i++){
        for (int j=0;j<MAX/2;j++){
            C[i][j]=A[i][0]*B[0][j]+A[i][1]*B[1][j];
    }}  

    List d = flat(C);
    MultReply response=MultReply.newBuilder().addAllC(d).build();
    reply.onNext(response);
    reply.onCompleted();
}//@Override
public void addblock(MultRequest request, StreamObserver<MultReply> reply)
{  
    int MAX = (int)Math.sqrt(request.getAList().size());
    int C[][]= new int[MAX][MAX];
    int A[][] = arr(request.getAList(),MAX);
    int B[][] = arr(request.getBList(),MAX);

    for (int i=0;i<MAX;i++){
        for (int j=0;j<MAX;j++){
            C[i][j]=A[i][j]+B[i][j];
        }
    }
    List d = flat(C);
    MultReply response=MultReply.newBuilder().addAllC(d).build();
    reply.onNext(response);
    reply.onCompleted();
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

