public class Calculadora {

    public static final int DIA_30_DE_JUNIO = 181;
    public static final int DIAS_MES = 30;
    public static final int DIAS_INDEMNIZACION_IMPROCEDENTE = 33;
    public static final int DIAS_INDEMNIZACION_PROCEDENTE = 20;

    public double calculaFiniquito(int diaFin, int mesFin, float aniosTrabajados,
                                   double salario, int numeroPagasExtras,
                                   int diasVacacionesUsados,
                                   String tipoDespido) {

        // Calcular días desde 1 de enero hasta fecha Fin de contrato
        int diasHastaFecha = getDiasHastaFecha(diaFin, mesFin);
        // Cálculo del salario del mes en curso
        double salarioPendiente = (salario / DIAS_MES) * diasHastaFecha;
        // Salario días trabajados
        // Cálculo de las pagas extras pendientes
        double pagasExtras = calculaPagasExtra(salario, diasHastaFecha);
        // Vacaciones
        double vacaciones = calculaVacaciones(salario, numeroPagasExtras, diasVacacionesUsados, diasHastaFecha);
        // Indemnización
        return calculaIndemnizacion(aniosTrabajados, salario, tipoDespido, salarioPendiente, pagasExtras, vacaciones);
    }

    public static double calculaVacaciones(double salario, int numeroPagasExtras, int diasVacacionesUsados, int diasHastaFecha) {
        double diasVacRestantes =(365/ DIAS_MES * diasHastaFecha) - diasVacacionesUsados;
        double vacaciones = diasVacRestantes*(salario *(12+ numeroPagasExtras) / 365);
        return vacaciones;
    }

    public static double calculaIndemnizacion(float aniosTrabajados, double salario, String tipoDespido, double salarioPendiente, double pagasExtras, double vacaciones) {
        double indemnizacion = 0;
        if(tipoDespido.equals("improcedente")) {
            indemnizacion = (salario / DIAS_MES) * DIAS_INDEMNIZACION_IMPROCEDENTE * aniosTrabajados;
        } else if(tipoDespido.equals("procedente")) {
            indemnizacion = (salario / DIAS_MES) * DIAS_INDEMNIZACION_PROCEDENTE * aniosTrabajados;
        }
        return salarioPendiente + pagasExtras + indemnizacion + vacaciones;
    }

    public static double calculaPagasExtra(double salario, int diasHastaFecha) {
        double pagasExtras = salario / diasHastaFecha * DIA_30_DE_JUNIO;
        if (diasHastaFecha >DIA_30_DE_JUNIO) {
            pagasExtras=pagasExtras- salario;
        }
        return pagasExtras;
    }

    public static int getDiasHastaFecha(int diaFin, int mesFin) {
        // diaFin/mesFin
        int[] diasPorMes = {31,28,31, DIAS_MES,31, DIAS_MES,31,31, DIAS_MES,31, DIAS_MES,31};
        int diasHastaFecha = 0;
        for (int i = 1; i < mesFin; i++) {
            diasHastaFecha =diasHastaFecha+ diasPorMes[i];
        }
        diasHastaFecha = diaFin;
        return diasHastaFecha;
    }
}
