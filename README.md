这是一个AspectJ 在Android中使用的简单例子


在方法中加 @MyLog 注解 ，会在调用该方法之前打印出该方法的日志。

参考：
http://www.jianshu.com/p/0fa8073fd144
http://blog.csdn.net/crazy__chen/article/details/52014672
待参考：
https://github.com/JakeWharton/hugo


最佳实践：



@Aspect
public class LPLogAspect {
  //  public String TAG = "";
    @Pointcut("execution(@com.zjlp.utils.log.LPLogPrint * *(..))")
    public void logMethod (){}

    @Around("logMethod()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        String TAG = getTag(joinPoint);//得到tag

        //方法执行前
        LPLog.print(TAG,getString(true,joinPoint,null,0));
        long startNanos = System.nanoTime();
        Object result = joinPoint.proceed();
        //方法执行后
        long lengthMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos);
        LPLog.print(TAG,getString(false,joinPoint,result,lengthMillis));
        return result;
    }


    public String getTag (ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LPLogPrint lpLogPrint = methodSignature.getMethod().getAnnotation(LPLogPrint.class);//得到注解
        return lpLogPrint.value();
    }

    public String getString (boolean isEnter,ProceedingJoinPoint joinPoint, Object result,long lengthMillis) {
        StringBuffer strBuf = new StringBuffer();
        Signature signature = joinPoint.getSignature();
        if (isEnter) {
            strBuf.append("→ ");
        } else {
            strBuf.append("← ");
        }
        strBuf.append(signature.getDeclaringTypeName()); //类名
        strBuf.append("." + signature.getName());//方法名
        strBuf.append(" (" ); //参数 S
        Object[] arges = joinPoint.getArgs();
        for (int i = 0;i < arges.length;i++) {
            if (i != 0) {
                strBuf.append(", ");
            }
            strBuf.append(arges[i]) ;
        }
        strBuf.append(")" );//参数 E
        if (isEnter) {
            return strBuf.toString();
        }
        if (((MethodSignature) signature).getReturnType() != void.class) {//结果
            strBuf.append(" = " );
            strBuf.append(result);
        }
        strBuf.append(" [" +
                lengthMillis +
                "ms]");

        return strBuf.toString();
    }

}
