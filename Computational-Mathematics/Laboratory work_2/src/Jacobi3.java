/**
 * Created by Алексей on 23.11.2015.
 */
public class Jacobi3 {
    int N1, n = 1000, k = 0;//переменные для выхода и цикла
    float[][] A, A_1, D, E, x, alpha;// вычисляемые приближения для корня
    float[] B, beta;
    float s = 0, maxim = 0;
    double eps = 0.0001;//  необходимая точность

    public Jacobi3(int numberUnknowns,
                   float[][] coefficientMatrix,
                   float[] vectorRightParts,
                   float[][] d,
                   float[] x0) {
        A = coefficientMatrix;
        B = vectorRightParts;
        D = d;
        x = new float[100][numberUnknowns];
        for (int i = 0; i < numberUnknowns; i++) {
            x[i][k] = x0[i];
        }
        N1 = numberUnknowns;
        alpha = new float[4][4];
        beta = new float[4];
    }

    public float[] jacobi() {

        for (int i = 0; i < N1; i++) //расчитываем альфу (по всем правилам перемножения матриц) исходную матрица А умножаем на матрице с элементами 0.001
        {
            for (int j = 0; j < N1; j++) {
                s = 0;
                for (int h = 0; h < N1; h++) {
                    s = (float) (s + A[j][h] * 0.001);
                }
                alpha[i][j] = s;
            }
        }

        for (int i = 0; i < N1; i++) //расчитываем бета с помощью вспомогательной матрицы D
        {
            s = 0;
            for (int j = 1; j < N1; j++) {
                s = s + (D[i][j] * B[j]);
            }
            beta[i] = s;
        }
        k++;
        for (int i = 0; i < N1; i++)   //цикл для расчета иксов
        {
            x[k][i] = beta[i];  //присваиваем иксу для начала значение соответсвующее из вектора бета
            for (int j = 0; j < N1; j++) {
                x[k][i] = x[k][i] + alpha[i][j] * x[k - 1][j]; //расчитываем по формуле используя предудущее приближение
            }
        }

        do {
            maxim = 0;
            for (int i = 0; i < N1; i++)   //цикл для расчета иксов
            {
                x[k + 1][i] = beta[i];  //присваиваем иксу для начала значение соответсвующее из вектора бета
                for (int j = 0; j < N1; j++) {
                    x[k+1][i] = x[k + 1][i] + alpha[i][j] * x[k - 1][j]; //расчитываем по формуле используя предудущее приближение
                }
            }
            for (int i = 0; i < N1; i++) //цикл для расчета погрешности
            {
                if (Math.abs(x[k][i] - x[k - 1][i]) > maxim)
                    maxim = Math.abs(x[k][i] - x[k - 1][i]);
            }
            k++;
        }

        while ((maxim > eps) && (k != 100)); //если будет очень много итераций это не круто

        float[] resilt = new float[4];
        for (int i = 0; i < 4; i++) {
            resilt[i] = x[k][i];
        }
        return resilt;
    }
}
