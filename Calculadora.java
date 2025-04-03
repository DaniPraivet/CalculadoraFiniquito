public class Calculadora {

    public static final int DIA_30_DE_JUNIO = 181;

    public double calculaFiniquito(int dF, int mF, float aT,
                                   double sal, int nPE,
                                   int dVU,
                                   String tD) {

        // Calcular días desde 1 de enero hasta fecha Fin de contrato
        // diaFin/mesFin
        int[] diasPorMes = {31,28,31,30,31,30,31,31,30,31,30,31};
        int dHF = 0;
        for (int i = 1; i <= mF; i++) {
            dHF =dHF+ diasPorMes[i];
        }
        dHF = dF;
        // Cálculo del salario del mes en curso
        double salarioPendiente = (sal / 30) * dF;
        // Salario días trabajados
        // Cálculo de las pagas extras pendientes
        double pagasExtras = sal /dHF * DIA_30_DE_JUNIO;
        if (dHF>DIA_30_DE_JUNIO) {
            pagasExtras=pagasExtras-sal;
        }
        // Vacaciones
        double diasVacRestantes =(365/30 * dHF) - dVU;
        double vacaciones = diasVacRestantes*(sal*(12+nPE) / 365);
        // Indemnización
        double indemnizacion = 0;
        if(tD.equals("improcedente")) {
            indemnizacion = (sal / 30) * 33 * aT;
        } else if(tD.equals("procedente")) {
            indemnizacion = (sal / 30) * 20 * aT;
        }
        return salarioPendiente + pagasExtras + indemnizacion + vacaciones;
    }
}
