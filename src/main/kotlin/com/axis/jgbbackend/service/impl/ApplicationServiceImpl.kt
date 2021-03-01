package com.axis.jgbbackend.service.impl

import com.axis.jgbbackend.model.ReqData
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.repository.PersonalOfferLandingRepo
import com.axis.jgbbackend.service.ApplicationService
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.StringWriter
import java.util.*


@Service
class ApplicationServiceImpl(
        @Autowired
        private val applicationRepo: ApplicationRepo,
        @Autowired
        val offerRepo:PersonalOfferLandingRepo
) : ApplicationService {


    override fun getFilteredData(_id: String): ReqData {
        val app=offerRepo.findBy_id(_id).block()

        val ve = VelocityEngine()
        val props = Properties()
        props.put("file.resource.loader.path", "F:\\Maximus project - Copy\\backend\\project-jgb-backend\\src\\main\\kotlin\\webapp\\templates")
        ve.init(props)

        val t = ve.getTemplate("mapping.vm")
        println(t)
        val req=ReqData()
        val context = VelocityContext()

        context.put("req", req)
        context.put("app",app)
        val sw = StringWriter()
        t.merge(context, sw)

        // val req= ReqData(app!!.loanAmount, app.loanPurpose)
        return req
    }


}