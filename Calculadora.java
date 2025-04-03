public class Calculadora {

    public static final int DIA_30_DE_JUNIO = 181;

    public double calculaFiniquito(int diaFin, int mesFin, float aniosTrabajados,
                                   double salario, int numeroPagasExtras,
                                   int diasVacacionesUsados,
                                   String tipoDespido) {

        // Calcular días desde 1 de enero hasta fecha Fin de contrato
        int diasHastaFecha = getDiasHastaFecha(diaFin, mesFin);
        // Cálculo del salario del mes en curso
        double salarioPendiente = (salario / 30) * diasHastaFecha;
        // Salario días trabajados
        // Cálculo de las pagas extras pendientes
        double pagasExtras = salario /diasHastaFecha * DIA_30_DE_JUNIO;
        if (diasHastaFecha>DIA_30_DE_JUNIO) {
            pagasExtras=pagasExtras-salario;
        }
        // Vacaciones
        double diasVacRestantes =(365/30 * diasHastaFecha) - diasVacacionesUsados;
        double vacaciones = diasVacRestantes*(salario*(12+numeroPagasExtras) / 365);
        // Indemnización
        double indemnizacion = 0;
        if(tipoDespido.equals("improcedente")) {
            indemnizacion = (salario / 30) * 33 * aniosTrabajados;
        } else if(tipoDespido.equals("procedente")) {
            indemnizacion = (salario / 30) * 20 * aniosTrabajados;
        }
        return salarioPendiente + pagasExtras + indemnizacion + vacaciones;
    }

    private static int getDiasHastaFecha(int diaFin, int mesFin) {
        // diaFin/mesFin
        int[] diasPorMes = {31,28,31,30,31,30,31,31,30,31,30,31};
        int diasHastaFecha = 0;
        for (int i = 1; i < mesFin; i++) {
            diasHastaFecha =diasHastaFecha+ diasPorMes[i];
        }
        diasHastaFecha += diaFin;
        return diasHastaFecha;
    }
}
