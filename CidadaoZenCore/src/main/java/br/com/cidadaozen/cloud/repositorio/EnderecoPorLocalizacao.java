package br.com.cidadaozen.cloud.repositorio;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.cidadaozen.cloud.dominio.ErrorMessage;
import br.com.cidadaozen.cloud.dominio.MensagemException;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;

@Repository
public class EnderecoPorLocalizacao {

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	public static String clientId;
	public static String clientKey;

	public List<Endereco> consultarEndereco(Endereco endereco)
			throws MensagemException {

		List<Endereco> list = new ArrayList<Endereco>();

		try {
			final Geocoder geocoder = retornarGeoCode();

			GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
					.setLanguage(Constantes.PT).getGeocoderRequest();

			verificarTipoPesquisa(endereco, geocoderRequest);

			LOG.info("chamando google");
			GeocodeResponse geocoderResponse = geocoder
					.geocode(geocoderRequest);

			LOG.info("Status resposta: " + geocoderResponse.getStatus());

			if (Constantes.OK.equals(geocoderResponse.getStatus().name())) {

				for (GeocoderResult r : geocoderResponse.getResults()) {

					Endereco end = new Endereco();

					popularLatitudeLongitude(r, end);

					for (GeocoderAddressComponent ac : r.getAddressComponents()) {

						popularEndereco(end, ac);

					}

					if (!Constantes.validarNulo(end.getBairro())
							&& !Constantes.validarNulo(end.getCidade())
							&& !Constantes.validarNulo(end.getEstado())
							&& !Constantes.validarNulo(end.getPais())) {
						LOG.info("Endereco encontrado: " + end);
						list.add(end);
					}
				}
			}

		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.IO_EXCEPTION);

		} catch (InvalidKeyException e) {
			LOG.error(e.getMessage(), e);
			throw new MensagemException(ErrorMessage.GENERICO);
		}

		return list;
	}

	private Geocoder retornarGeoCode() throws InvalidKeyException {
		if (!Constantes.validarNulo(clientId)
				&& !Constantes.validarNulo(clientKey)) {
			LOG.info("new Geocoder: " + clientId + " | " + clientKey);
			return new Geocoder(clientId, clientKey);

		} else {
			LOG.info("new Geocoder");
			return new Geocoder();
		}
	}

	private void verificarTipoPesquisa(Endereco endereco,
			GeocoderRequest geocoderRequest) throws MensagemException {

		if (endereco.getLatitude() != 0 && endereco.getLongitude() != 0) {

			LOG.info("consultarEnderecoPorLocalizacao >> latitude e longitude");
			LatLng location = new LatLng(BigDecimal.valueOf(endereco
					.getLatitude()),
					BigDecimal.valueOf(endereco.getLongitude()));
			geocoderRequest.setLocation(location);

		} else {
			throw new MensagemException(ErrorMessage.DADOS_INVALIDOS);
		}
	}

	private void popularEndereco(Endereco end, GeocoderAddressComponent ac) {
		// if (isType(Constantes.LOGRADOURO, ac.getTypes(), ac.getShortName()))
		// {
		// end.setLogradouro(ac.getShortName());
		//
		// } else if (isType(Constantes.NUMERO, ac.getTypes(),
		// ac.getShortName())
		// && ac.getShortName().split("-").length == 1) {
		// end.setNumero(Integer.valueOf(ac.getShortName()));

		if (isType(Constantes.BAIRRO, ac.getTypes(), ac.getShortName())) {
			end.setBairro(ac.getShortName());

		} else if (isType(Constantes.CIDADE, ac.getTypes(), ac.getShortName())) {
			end.setCidade(ac.getShortName());

		} else if (isType(Constantes.ESTADO, ac.getTypes(), ac.getShortName())) {
			end.setEstado(ac.getShortName());

		} else if (isType(Constantes.PAIS, ac.getTypes(), ac.getShortName())) {
			end.setPais(ac.getShortName());

			// } else if (isType(Constantes.CEP, ac.getTypes(),
			// ac.getShortName())) {
			// end.setCep(ac.getShortName());
		}
	}

	private void popularLatitudeLongitude(GeocoderResult r, Endereco end) {
		if (null != r.getGeometry().getBounds()
				&& null != r.getGeometry().getBounds().getSouthwest()) {

			end.setLatitude(r.getGeometry().getBounds().getSouthwest().getLat()
					.doubleValue());
			end.setLongitude(r.getGeometry().getBounds().getSouthwest()
					.getLng().doubleValue());

		} else if (null != r.getGeometry().getLocation()) {
			end.setLatitude(r.getGeometry().getLocation().getLat()
					.doubleValue());
			end.setLongitude(r.getGeometry().getLocation().getLng()
					.doubleValue());

		}
	}

	private boolean isType(String valorProcurado, List<String> types,
			String valor) {

		for (String type : types) {
			if (valorProcurado.equals(type) && null != valor
					&& valor.trim().length() != 0) {
				return true;
			}
		}
		return false;
	}
}
