package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriToimii() {
        varasto = new Varasto(20, 20);
        assertThat(varasto.getSaldo(), is(20.0));
    }

    @Test
    public void negatiivinenTilavuusOnNolla() {
        varasto = new Varasto(-20);
        assertThat(varasto.getTilavuus(), is(0.0));
    }

    @Test
    public void negatiivinenTilavuusOnNolla2() {
        varasto = new Varasto(-20, 20);
        assertThat(varasto.getTilavuus(), is(0.0));
    }

    @Test
    public void negatiivinenSaldoOnNolla() {
        varasto = new Varasto(20, -10);
        assertThat(varasto.getSaldo(), is(0.0));
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoaEiVoiLisataLiikaa() {
        varasto.lisaaVarastoon(20);
        assertThat(varasto.getSaldo(), is(10.0));
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void kaikenOttamienSaldoOnNolla() {
        varasto.otaVarastosta(20.0);
        assertThat(varasto.getSaldo(), is(0.0));
    }

    @Test
    public void negatiivisenOttaminenEiToimi() {
        assertThat(varasto.otaVarastosta(-20), is(0.0));
    }

    @Test
    public void negatiivisenLisaaminenEiToimi() {
        varasto.lisaaVarastoon(-20);
        assertThat(varasto.getSaldo(), is(0.0));
    }

    @Test
    public void ToStringTest() {
        assertThat(varasto.toString(), is("saldo = 0.0, vielä tilaa 10.0"));
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}