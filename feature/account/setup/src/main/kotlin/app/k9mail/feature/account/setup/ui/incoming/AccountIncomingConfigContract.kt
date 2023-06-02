package app.k9mail.feature.account.setup.ui.incoming

import app.k9mail.core.ui.compose.common.mvi.UnidirectionalViewModel
import app.k9mail.feature.account.setup.domain.entity.ConnectionSecurity
import app.k9mail.feature.account.setup.domain.entity.IncomingProtocolType
import app.k9mail.feature.account.setup.domain.entity.toImapDefaultPort
import app.k9mail.feature.account.setup.domain.input.NumberInputField
import app.k9mail.feature.account.setup.domain.input.StringInputField

interface AccountIncomingConfigContract {

    interface ViewModel : UnidirectionalViewModel<State, Event, Effect> {
        fun initState(state: State)
    }

    data class State(
        val protocolType: IncomingProtocolType = IncomingProtocolType.DEFAULT,
        val server: StringInputField = StringInputField(),
        val security: ConnectionSecurity = ConnectionSecurity.DEFAULT,
        val port: NumberInputField = NumberInputField(ConnectionSecurity.DEFAULT.toImapDefaultPort()),
        val username: StringInputField = StringInputField(),
        val password: StringInputField = StringInputField(),
        val clientCertificate: String = "",
        val imapAutodetectNamespaceEnabled: Boolean = true,
        val imapPrefix: StringInputField = StringInputField(),
        val useCompression: Boolean = true,
    )

    sealed class Event {
        data class ProtocolTypeChanged(val protocolType: IncomingProtocolType) : Event()
        data class ServerChanged(val server: String) : Event()
        data class SecurityChanged(val security: ConnectionSecurity) : Event()
        data class PortChanged(val port: Long?) : Event()
        data class UsernameChanged(val username: String) : Event()
        data class PasswordChanged(val password: String) : Event()
        data class ClientCertificateChanged(val clientCertificate: String) : Event()
        data class ImapAutoDetectNamespaceChanged(val enabled: Boolean) : Event()
        data class ImapPrefixChanged(val imapPrefix: String) : Event()
        data class UseCompressionChanged(val useCompression: Boolean) : Event()
        object OnNextClicked : Event()
        object OnBackClicked : Event()
    }

    sealed class Effect {
        object NavigateNext : Effect()
        object NavigateBack : Effect()
    }
}
