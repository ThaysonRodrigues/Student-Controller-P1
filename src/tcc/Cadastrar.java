package tcc;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCapturePriority;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.util.EnumMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Cadastrar {

    public static void main(String[] args) throws InterruptedException {
        
        // Variaveis...
        EnumMap<DPFPFingerIndex, DPFPTemplate> templates;
        templates = new EnumMap<DPFPFingerIndex, DPFPTemplate>(
                DPFPFingerIndex.class);

        // Inicio do cadastro da digital
        Funcoes funcao = new Funcoes();

        System.out.println("Cadastrando digital...");

        try {
            DPFPFingerIndex finger = DPFPFingerIndex.values()[7];
            DPFPFeatureExtraction featureExtractor = DPFPGlobal
                    .getFeatureExtractionFactory().createFeatureExtraction();
            DPFPEnrollment enrollment = DPFPGlobal.getEnrollmentFactory()
                    .createEnrollment();

            // String activeReader = null;
            while (enrollment.getFeaturesNeeded() > 0) {
                DPFPSample sample = getSample(
                        null,
                        String.format(
                                "Registre a digital do indicador da m�o direita (Faltam %d leituras)\n",
                                enrollment.getFeaturesNeeded()));
                if (sample == null) {
                    continue;
                }

                DPFPFeatureSet featureSet;
                try {
                    featureSet = featureExtractor.createFeatureSet(sample,
                            DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
                } catch (DPFPImageQualityException e) {
                    System.out.printf(
                            "M� qualidade: \"%s\". Tente novamente. \n", e
                            .getCaptureFeedback().toString());
                    continue;
                }

                enrollment.addFeatures(featureSet);
            }

            DPFPTemplate template = enrollment.getTemplate();
            // user.setTemplate(finger, template);
            templates.put(finger, template);

            System.out.print("Cadastrado com sucesso!\n");
            
            //proximas 3 linhas mandam para o banco o dedo cadastrado
            
            byte bytes[] = template.serialize();
            funcao.conectar();
            funcao.MandaFormatado(bytes);

        } catch (DPFPImageQualityException e) {
            System.out.printf("Falha ao cadastrar digital.\n\n");
        }
		// Fim do cadastro da digital
    }
    private static DPFPSample getSample(String activeReader, String prompt)
			throws InterruptedException {
		final LinkedBlockingQueue<DPFPSample> samples = new LinkedBlockingQueue<DPFPSample>();
		DPFPCapture capture = DPFPGlobal.getCaptureFactory().createCapture();
		capture.setReaderSerialNumber(activeReader);
		capture.setPriority(DPFPCapturePriority.CAPTURE_PRIORITY_LOW);
		capture.addDataListener(new DPFPDataListener() {
			public void dataAcquired(DPFPDataEvent e) {
				if (e != null && e.getSample() != null) {
					try {
						samples.put(e.getSample());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		capture.addReaderStatusListener(new DPFPReaderStatusAdapter() {
			int lastStatus = DPFPReaderStatusEvent.READER_CONNECTED;

			public void readerConnected(DPFPReaderStatusEvent e) {
				if (lastStatus != e.getReaderStatus())
					System.out.println("Leitor conectado!");
				lastStatus = e.getReaderStatus();
			}

			public void readerDisconnected(DPFPReaderStatusEvent e) {
				if (lastStatus != e.getReaderStatus())
					System.out.println("O leitor esta desconectado!");
				lastStatus = e.getReaderStatus();
			}

		});
		try {
			capture.startCapture();
			System.out.print(prompt);
			return samples.take();
		} catch (RuntimeException e) {
			System.out
					.printf("Falha ao iniciar captura. Verifique se o leitor n�o esta sendo usado por outra applica��o.\n");
			throw e;
		} finally {
			capture.stopCapture();
		}
	}
    
}
